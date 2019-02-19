package api.test;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class BasicsAssured {

@Test
public  void test() {
		
		//Base url or host
		RestAssured.baseURI="https://maps.googleapis.com"; //URL
		
		given().
		param("location", "-33.8670522,151.1957362").
		param("radius", "1500").
		param("key", "AIzaSyAoEyrY8dlqoofhr_DgL6UygLZf0XToBQE").
		when().
		get("/maps/api/place/nearbysearch/json"). //resources or URN
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("results[0].name", equalTo("Sydney")).and().
		body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
		header("Server", "scaffolding on HTTPServer2");
		
		/* status code of the response
		   content Type
		   Body

		 */
	}

}
