package java.day7;


import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;


public class GetRequest {
	
	@Test
	void getRequest(ITestContext context) {
		
		String bearerToken = "";
		int id = (Integer) context.getAttribute("userId");
		
		
		given()
			.header("Authorization","Bearer " + bearerToken)
			.contentType("application/json")
			.pathParam("id", id)
		.when()
			.get("https://url/{id}")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json")
			.log().all();
	}
}
