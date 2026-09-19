package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/*
 * 1. By using the HashMap
 * 2. By using the org.Json
 * 3. By using the POJO Class
 * 4. By using the external JSON file
 */

public class DifferentWaysToCreatePostRequest {

	@Test(priority=1)
	void testPostUsingHashMap() {
		HashMap<String, Object> data = new HashMap<>();
		
		data.put("name", "Kunj");
		data.put("location", "Pune");
		data.put("phone number", "12344");
		
		String courseArr[] = {"Java", "C++"};
		
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://url/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Kunj"))
			.body("location", equalTo("Pune"))
			.body("phone number", equalTo("12344"))
			.body("courses[0]",equalTo("Java" ))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", "application/json")
			.log().all();
	}
	
	@Test(priority=3)
	void testPostUsingOrgJsonLibrary() {
		JSONObject data = new JSONObject();
		data.put("name", "Suhani");
		data.put("location", "Vidisha");
		data.put("phone number", "12344");
		
		String courseArr[] = {"Java", "C++"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://url/student")
		.then()
			.statusCode(201)
			.header("Content-Type", "application/json")
			.body("name", equalTo("Suhani"))
			.body("courses[0]", equalTo("Java"))
			.log().all();
	}
	
	@Test
	void usingExternalJsonFile() throws FileNotFoundException {
		File file = new File("filepath");
		FileReader fr = new FileReader(file);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject jb = new JSONObject(jt);
		
		given()
			.contentType("application/json")
			.body(jb.toString())
		.when()
			.post("https://url/student")
		.then()
			.statusCode(201)
			.header("Content-Type", "application/json")
			.body("name", equalTo("Suhani"))
			.body("courses[0]", equalTo("Java"))
			.log().all();
	}	
	
	@Test(priority = 2)
	void deteleRecord() {
		when()
			.delete("https://url/students/4")
		.then()
			.statusCode(200)
			.log().all();
	}
}