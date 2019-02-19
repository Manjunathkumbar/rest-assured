package api.test;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resource.ReusableMetods;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class BasicsAssured2 {

@Test
public  void test() {
		
		//Base url or host
		RestAssured.baseURI="https://maps.googleapis.com";
		
		Response res = given().
		param("location", "-33.8670522,151.1957362").
		param("radius", "1500").
		param("key", "AIzaSyAoEyrY8dlqoofhr_DgL6UygLZf0XToBQE").
		when().
		get("/maps/api/place/nearbysearch/json"). //resources
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("results[0].name", equalTo("Sydney")).and().
		body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
		header("Server", "scaffolding on HTTPServer2").log().all().
		extract().response();
		
		JsonPath js = ReusableMetods.rawToJsonPath(res);
		int count = js.get("results.size()");
		System.out.println(count);
		for (int i=0; i<count; i++)
		{
			String name = js.get("results["+i+ "].name");
			System.out.println(name);
		}
		
		/* status code of the response
		   content Type
		   Body

		 */
	}

}
