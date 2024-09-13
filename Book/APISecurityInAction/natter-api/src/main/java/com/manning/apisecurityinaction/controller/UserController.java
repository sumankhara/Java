package com.manning.apisecurityinaction.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.dalesbred.Database;
import org.json.JSONObject;

import com.lambdaworks.crypto.SCryptUtil;

import spark.Request;
import spark.Response;

public class UserController {

	private static final String USERNAME_PATTERN = "[a-zA-Z][a-zA-Z0-9]{1,29}";
	
	private final Database database;

	public UserController(Database database) {
		this.database = database;
	}
	
	public JSONObject registerUser(Request request, Response response) {
		var json = new JSONObject(request.body());
		
		var username = json.getString("username");
		var password = json.getString("password");
		
		if(!username.matches(USERNAME_PATTERN)) {
			throw new IllegalArgumentException("invalid username");
		}
		
		if(password.length() < 8) {
			throw new 	IllegalArgumentException("password must be at least 8 characters");
		}
		
		var hash = SCryptUtil.scrypt(password, 32768, 8, 1);
		
		database.updateUnique("INSERT INTO users(user_id, pw_hash) VALUES(?, ?)", username, hash);
		
		response.status(201);
		response.header("Location", "/users/" + username);
		
		return new JSONObject().put("username", username);
	}
	
	public void authenticate(Request request, Response response) {
		var authHeader = request.headers("Authorization");
		if(authHeader == null || !authHeader.startsWith("Basic")) {
			return;
		}
		
		var offset = "Basic ".length();
		var credentials = new String(Base64.getDecoder().decode(authHeader.substring(offset)), StandardCharsets.UTF_8);
		
		var components = credentials.split(":", 2);
		if(components.length != 2) {
			throw new IllegalArgumentException("invalid auth header");
		}
		
		var username = components[0];
		var password = components[1];
		
		if(!username.matches(USERNAME_PATTERN)) {
			throw new IllegalArgumentException("invalid username");
		}
		
		var hash = database.findOptional(String.class, "SELECT pw_hash FROM users WHERE user_id = ?", username);
		
		if(hash.isPresent() && SCryptUtil.check(password, hash.get())) {
			request.attribute("subject", username);
		}
	}
}
