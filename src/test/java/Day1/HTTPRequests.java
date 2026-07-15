package Day1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HTTPRequests {

    @Test(priority = 1)
    void getUsers() {

        when()
            .get("https://jsonplaceholder.typicode.com/todos")

        .then()
            .statusCode(200)
            .body("[1].id", equalTo(2))
            .log().all();
    }

    @Test(priority = 2)
    void createUser() {

        HashMap<String, Object> map = new HashMap<>();

        map.put("title", "POST Req");
        map.put("body", "This is the first POST Request");
        map.put("userId", 1);

        given()
            .contentType("application/json")
            .body(map)

        .when()
            .post("https://jsonplaceholder.typicode.com/posts")

        .then()
            .statusCode(201)
            .log().all();
    }

    @Test(priority = 3)
    void updateUser() {

        HashMap<String, Object> map = new HashMap<>();

        map.put("title", "PUT Req");
        map.put("body", "This is the first PUT Request");
        map.put("userId", 1);

        given()
            .contentType("application/json")
            .body(map)

        .when()
            .put("https://jsonplaceholder.typicode.com/posts/1")

        .then()
            .statusCode(200)
            .log().all();
    }

    @Test(priority = 4, dependsOnMethods = {"updateUser"})
    void deleteUser() {

        given()

        .when()
            .delete("https://jsonplaceholder.typicode.com/posts/1")

        .then()
            .statusCode(200)
            .log().all();
    }
}