package day3;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

public class PathAndQueryParameters {
	@Test(priority=1)
	void testQueryAndPathParameter() {
		given()
			.pathParam("myParam", "users")
			.queryParam("page", 2)
			.queryParam("id", 5)
		.when()
			.get("https://url/api/{myParam}") // we only define the path parameter as variables not the query parameter
		.then()
			.statusCode(200)
			.log().all();
	}
}
