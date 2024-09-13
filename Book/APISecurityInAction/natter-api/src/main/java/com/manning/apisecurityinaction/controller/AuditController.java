package com.manning.apisecurityinaction.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.dalesbred.Database;
import org.json.JSONArray;
import org.json.JSONObject;

import spark.Request;
import spark.Response;

public class AuditController {

	private final Database database;

	public AuditController(Database database) {
		this.database = database;
	}
	
	public void auditRequestStart(Request request, Response response) {
		database.withVoidTransaction(tx -> {
			var auditId = database.findUniqueLong("SELECT NEXT VALUE FOR audit_id_seq");
			request.attribute("auditId", auditId);
			
			database.updateUnique("INSERT INTO audit_log(audit_id, method, path, user_id, audit_time) " +
					"VALUES (?, ?, ?, ?, current_timestamp)",
					auditId,
					request.requestMethod(),
					request.pathInfo(),
					request.attribute("subject"));
		});
	}
	
	public void auditRequestEnd(Request request, Response response) {
		database.withVoidTransaction(tx -> {
			var auditId = database.findUniqueLong("SELECT NEXT VALUE FOR audit_id_seq");
			request.attribute("auditId", auditId);
			
			database.updateUnique("INSERT INTO audit_log(audit_id, method, path, user_id, status, audit_time) " +
					"VALUES (?, ?, ?, ?, ?, current_timestamp)",
					auditId,
					request.requestMethod(),
					request.pathInfo(),
					request.attribute("subject"),
					response.status());
		});
	}
	
	public JSONArray readAuditLog(Request request, Response response) {
	    var since = Instant.now().minus(1, ChronoUnit.HOURS);
	    var logs = database.findAll(AuditController::recordToJson,
	            "SELECT * FROM audit_log " +
	                    "WHERE audit_time >= ? LIMIT 20", since);
	    return new JSONArray(logs);
	}
	 
	private static JSONObject recordToJson(ResultSet row)
	        throws SQLException {
	    return new JSONObject()
	            .put("id", row.getLong("audit_id"))
	            .put("method", row.getString("method"))
	            .put("path", row.getString("path"))
	            .put("status", row.getInt("status"))
	            .put("user", row.getString("user_id"))
	            .put("time", row.getTimestamp("audit_time").toInstant());
	}
}
