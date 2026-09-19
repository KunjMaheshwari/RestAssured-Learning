package java.day5;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class ParingReponseBody {

	@Test(priority=1)
	void validateJsonRespone() {
		
		//Approach 1
		/*
		 * given()
			.contentType("application/json")
		.when()
			.get("url")
		.then()
			.statusCode(200)
			.body("book[3].title", equalTo("The Lord of the kings"))
			.header("Content-Type", "application/json");
		*/
		
		//Approach 2
		Response res = given()
			.contentType("application.json")
		.when()
			.get("url");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getHeader("Content-Type"), "application/json");
		
		// How to get all the titles from the response
		JSONObject jo = new JSONObject(res.toString());
		
		for(int i=0;i<jo.getJSONArray("book").length();i++) {
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).getString("title").toString();
			System.out.println(bookTitle);
		}
	}
}
