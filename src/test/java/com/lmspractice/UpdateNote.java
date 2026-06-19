package com.lmspractice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateNote {

	@Test
	public void updateNote() {
		Map<String, Object> payload = new HashMap<>();
		payload.put("email", "sam@gmail.com");
		payload.put("password", "123");
		Response response = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(payload)
				.when()
				.post("https://lms-server-3-wedg.onrender.com/user/login");
		String token = response.jsonPath().getString("token");
		Map<String, Object> fields = new HashMap<>();
		ArrayList<String> tags = new ArrayList<>();
		tags.add("Updated");
		tags.add("API");
		fields.put("title", "Updated Note");
		fields.put("content", "Updated using Rest Assured");
		fields.put("tags", tags);
		fields.put("isPinned", false);
		fields.put("color", "#0000ff");
		Response res = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer " + token)
				.body(fields)
				.when()
				.put("https://lms-server-3-wedg.onrender.com/update/notes/6a337a4c026d1fff76738368");
		res.prettyPrint();
		res.then()
		.statusCode(200);
	}
}