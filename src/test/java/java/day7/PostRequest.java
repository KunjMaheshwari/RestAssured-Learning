package java.day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class PostRequest {
	
	@Test
	void postRequest(ITestContext context) {
		String bearerToken = "";
		int id = 0;
		
		id = given()
			.header("Authorization", "Bearer "+ bearerToken)
			.contentType("application/json")
		.when()
			.post("url")
			.jsonPath().getInt("id");
		
		context.setAttribute("userId", id);
	}
}
