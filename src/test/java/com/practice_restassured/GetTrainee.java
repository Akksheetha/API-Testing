package com.practice_restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetTrainee {
	
	@Test
	public void get_trainee() {
		Response res = RestAssured
				.given().when()
				.get("http://localhost:3000/trainees/1");
		res.prettyPrint();
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.jsonPath().getString("name"),"Akkshee");
	}

}
