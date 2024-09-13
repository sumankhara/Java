package com.manning.apisecurityinaction;

import static spark.Spark.*;

import com.google.common.util.concurrent.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.dalesbred.Database;
import org.dalesbred.result.EmptyResultException;
import org.h2.jdbcx.JdbcConnectionPool;
import org.json.JSONException;
import org.json.JSONObject;

import com.manning.apisecurityinaction.controller.AuditController;
import com.manning.apisecurityinaction.controller.SpaceController;
import com.manning.apisecurityinaction.controller.UserController;

import spark.Request;
import spark.Response;

public class Main {

	public static void main(String[] args) throws Exception {
		var datasource = JdbcConnectionPool.create("jdbc:h2:mem:natter", "natter", "password");
		var database = Database.forDataSource(datasource);
		createTables(database);
		
		datasource = JdbcConnectionPool.create("jdbc:h2:mem:natter", "natter_api_user", "password");
		database = Database.forDataSource(datasource);
		
		var spaceController = new SpaceController(database);
		
		var userController = new UserController(database);
		
		var rateLimiter = RateLimiter.create(2.0d);
		
		before((request, response) -> {
			if(!rateLimiter.tryAcquire()) {
				response.header("Retry-After", "2");
				halt(429);
			}
		});
		
		before(userController::authenticate);
		
		var auditController = new AuditController(database);
		before(auditController::auditRequestStart);
		afterAfter(auditController::auditRequestEnd);
		
		post("/users", userController::registerUser);
		
		post("/spaces", spaceController::createSpace);
		
		get("/logs", auditController::readAuditLog);
		
		before((request, response) -> {
			if(request.requestMethod().equals("POST") &&
					!"application/json".equals(request.contentType())) {
				halt(415, new JSONObject()
						.put("error", "Only application/json supported").toString());
			}
		});
		
		after((request, response) -> {
			response.type("application/json");
		});
		
		internalServerError(new JSONObject()
				.put("error", "internal server error").toString());
		notFound(new JSONObject()
				.put("error", "not found").toString());
		
		exception(IllegalArgumentException.class, Main::badRequest);
		exception(JSONException.class, Main::badRequest);
		exception(EmptyResultException.class, (ex, request, response) -> response.status(404));
		
		afterAfter((request, response) -> {
			response.type("application/json;charset=utf-8");
			response.header("X-Content-Type-Options", "nosniff");
			response.header("X-Frame-Options", "DENY");
			response.header("X-XSS-Protection", "0");
			response.header("Cache-Control", "no-store");
			response.header("Content-Security-Policy",
			        "default-src 'none'; frame-ancestors 'none'; sandbox");
			response.header("Server", "");
		});
		
	}
	
	private static void createTables(Database database) throws Exception {
		Path path = Paths.get(Main.class.getResource("/schema.sql").toURI());
		database.update(Files.readString(path));
	}
	
	private static void badRequest(Exception ex, Request request, Response response) {
		response.status(400);
		response.body(new JSONObject().put("error", ex.getMessage()).toString());
	}
}
