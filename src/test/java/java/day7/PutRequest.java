package java.day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class PutRequest {

	@Test
	void putRequest(ITestContext context) {
		
		String bearerToken = "";
		int id =(Integer) context.getAttribute("userId");
		
		HashMap<String, String> map = new HashMap<>();
		Faker faker = new Faker();
		
		map.put("firstName", faker.name().firstName());
		map.put("email", faker.internet().emailAddress());
		
		
		given()
			.header("Authorization", "Bearer "+bearerToken)
			.contentType("application/json")
			.pathParam("id", id)
			.body(map)
		.when()
			.put("https://google.com/{id}")
		.then()
			.statusCode(200)
			.log().all();
	}
}
