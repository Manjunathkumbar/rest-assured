package resource;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class ReusableMetods {
	
	public static JsonPath rawToJsonPath(Response res)
	{
		
		String responsestring = res.asString();
		System.out.println(responsestring);
		JsonPath js = new JsonPath(responsestring);
		return js;
		
	}

}
