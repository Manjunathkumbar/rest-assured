package api.test;

import resource.Payload;
import resource.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestassuredAddAndDelete {
	
	Properties prop = new Properties();

	@BeforeTest
	public void getData() throws IOException
	{
		FileInputStream fis = new FileInputStream("D:\\MANJUAUTOMATION\\workspace\\"
				+ "rest-assured\\src\\test\\java\\config\\config.properties");
		prop.load(fis);
	}
	
	@Test
	public void addAndDeleteplace() throws InterruptedException
	{
				//Base url or host
				RestAssured.baseURI=prop.getProperty("HOST");
						
				Response res = given().
				queryParam("key", prop.getProperty("key")).
				body(Payload.getpostData()).
				
				when().
				post(resources.placepostData()).
				then().assertThat().statusCode(200).contentType(ContentType.JSON).and().
				body("status", equalTo("OK")).
				extract().response();	
				
				//Grab the place_ id from the response
				
				/*String responsestring = res.asString();
				System.out.println(responsestring);
				JsonPath js = new JsonPath(responsestring);*/
				JsonPath js = ReusableMetods.rawToJsonPath(res);
				Object placeid = js.get("place_id");
				System.out.println("place id is :" + placeid);
				
				//delete the place id
				Thread.sleep(3000);
				
				 given().
				queryParam("key", "qaclick123").
				
				body("{" + 
						"\"place_id\":\" "+ placeid +"\"" + 
						"}").
				
				when().
				post("/maps/api/place/delete/json").
				then().assertThat().contentType(ContentType.JSON).and().
				body("msg", equalTo("Delete operation failed, looks like the data doesn't exists"));
			
	}

}