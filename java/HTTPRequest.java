
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 * given()
 * 	content type, add authentication, add headers, add parameters
 * .when()
 * 	request type get, post, put, delete
 *.then()
 * 	validate the response, status code, put assertions
 */


public class HTTPRequest {
	
	String name;
	
	@Test(priority = 1)
	void getUsers() {
		given()
		
		.when()
			.get("https://jsonplaceholder.typicode.com/todos/1")
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}
	
	@Test(priority=2)
	void createUser() {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(1, "Kunj Maheshwari");
		map.put(2, "Suhani Soni");
		
		name = given()
			.contentType("application/json")
			.body(map)
		.when()
			.post("https://jsonplaceholder.typicode.com/todos")
			.jsonPath().getString("name");
			
//		.then()
//			.statusCode(201)
//			.log().all();
	}
	
	@Test(priority = 3, dependsOnMethods = {"createUser"})
	void updateUser() {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(1, "Kunj Maheshwari");
		map.put(2, "Suhani Maheshwari");
		
		
		given()
			.contentType("application/json")
			.body(map)
		.when()
			.put("https://jsonplaceholder.typicode.com/todos" + name)
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority=4)
	void deleteUser() {
		when()
			.delete("https://jsonplaceholder.typicode.com/todos" + name)
		.then()
			.statusCode(204)
			.log().all();
	}
}
