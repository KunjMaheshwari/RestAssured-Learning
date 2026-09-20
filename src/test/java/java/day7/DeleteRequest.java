package java.day7;

import org.testng.annotations.Test;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class DeleteRequest {
	
	@Test
	void deleteRequest(ITestContext context) {
		String bearerToken = "";
		
		int id = (Integer) context.getAttribute("userId");
		
		given()
			.header("Authorization", "Bearer "+bearerToken)
			.contentType("application/json")
			.pathParams("id", id)
		.when()
			.delete("https://url/{id}")
		.then()
			.statusCode(200)
			.log().all();
	}
}
