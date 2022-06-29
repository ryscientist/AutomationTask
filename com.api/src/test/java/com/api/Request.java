package com.api;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Request {

	@Test(enabled = true)
	public void getUsers() {
		RequestSpecification req = RestAssured.given();
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

		given().when().get("https://jsonplaceholder.typicode.com").then().assertThat().statusCode(200);

		Response res = given().contentType(ContentType.JSON).when().get("/users?userId=1").then().extract().response();

		String resString = JsonPath.from(res.asString()).getString("username"); // Getting the first username from json

		Assert.assertEquals(resString, "Bret"); // Verifying first name from the json
	}

	@Test(enabled = true)
	public void getPostId() {
		RequestSpecification req = RestAssured.given();
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

		given().when().get("https://jsonplaceholder.typicode.com").then().assertThat().statusCode(200);

		Response res = given().contentType(ContentType.JSON).when().get("/posts?userId=1").then().extract().response();

		String resString = JsonPath.from(res.asString()).getString("id"); // getting the posts for first user ID

		Assert.assertNotNull(resString); // Verifying that postId is not null

	}

	@Test(enabled = true)
	public void postId() {

		RequestSpecification req = RestAssured.given();
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

		String payload = "{\r\n" + "    \"title\": \"foo\",\r\n" + "    \"body\": \"bar\",\r\n"
				+ "    \"userId\": 1\r\n" + "}";

		given().when().get("https://jsonplaceholder.typicode.com").then().assertThat().statusCode(200);

		Response res = given().contentType(ContentType.JSON).when().body(payload).post("/posts?userId=1").then()
				.extract().response();

		String resString = JsonPath.from(res.asString()).getString("id");

		Assert.assertEquals(resString, 101);

	}

}
