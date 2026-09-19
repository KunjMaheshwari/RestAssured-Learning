package java.day3;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class CookiesAndHeaders {
	
	//@Test(priority=1)
//	void testCookies() {
//		given()
//			
//		.when()
//			.get("https://www.google.com")
//		.then()
//			.statusCode(200)
//			.cookie("NID", "CvIBCAESpAEBp2sxruYRsB-u4Nhxr-dJ_m2pAObsPlDullWRWe2yhwAudfaPydQGFQspS0h4GNyiKtgRQTuO5zhuZ50ZKCaTu6zx8LVPnlZEcXkr-7gNV7wD1dNAMbApQV7q1dn9slBoWx0hce5W-yhqv27TFXVm-3ucnJOJnsGxKYW4wISEKwPp7GNHgYQ24dwMrNqFTW5HlKBcyj3UR5Ij2qTyGKGR7y1_-SgBMkUBDiveEeC-ruDFuwo8JON0OLr1t7kefMJO_TanXmEozFjHYAxQCD22m7nRSFAbcRUxdkVB2i0rqIgeQym05JklMJ5FBA8")
//			.log().all();
//	}
	
	@Test(priority=2)
	void getCookieInfo() {
		Response res = given()
		.when()
			.get("https://www.google.com");
		
		String cookie_Value = res.getCookie("NID");
		System.out.println("Value of cookie is: "+ cookie_Value);
	}
	
	@Test(priority=3)
	void getHeader() {
		Response res = given()
		.when()
			.get("https://www.google.com");
		
		String headerValue = res.getHeader("Content-Type");
		System.out.println("Value of header is: "+ headerValue);
		
		Headers myheaders = res.getHeaders();
		
		for(Header hd: myheaders) {
			System.out.println(hd.getName()+" " + hd.getValue());
		}
	}
}