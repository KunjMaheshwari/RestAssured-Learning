package java.day6;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class Authentication {

	@Test(priority=1)
	void basicAuthentication() {
		given()
			.auth().basic("username", "password")
		.when()
			.get("")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test(priority=2)
	void digestAuthentication() {
		given()
			.auth().digest("username", "password")
		.when()
			.get("")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test(priority = 3)
	void preemptiveAuthentication() {

	    given()
	        .auth()
	        .preemptive()
	        .basic("username", "password")
	    .when()
	        .get("https://example.com/basic-auth")
	    .then()
	        .statusCode(200)
	        .body("authenticated", equalTo(true))
	        .log().all();
	}
	
	@Test(priority=4)
	void bearerTokenAuthentication() {
		String bearerToken = "";
		
		given()
			.header("Authorization", "Bearer "+ bearerToken)
		.when()
			.get()
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test(priority=5)
	void fakerLibrary() {
		Faker faker = new Faker();
		
		HashMap<String, String> map = new HashMap<>();
			
		map.put("name", faker.name().firstName());
		map.put("lastName", faker.name().lastName());
		
		given()
			.contentType("application/json")
			.body(map)
		.when()
			.get("")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	
}
