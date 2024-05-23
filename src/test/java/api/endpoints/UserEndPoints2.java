package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.UserPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.*;

public class UserEndPoints2 {
	
	// method created for getting url's from properties file
	
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes");//Load the properties file "routes" is a property file name
		return routes;
	}
	
	public static Response create_user(UserPOJO payload)
	{
		
		String post_url = getURL().getString("post_url");
		
		Response response = given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .body(payload)
		
		.when()
		    .post(post_url);
		
		return response;
	}
	
	public static Response read_user(String userName)
	{
		
		String get_url = getURL().getString("get_url");
		
		Response response = given()
		                        .pathParam("username", userName)
		
		.when()
		    .get(get_url);
		
		return response;
	}
	
	public static Response update_user(String userName, UserPOJO payload)
	{
		
		String put_url = getURL().getString("update_url");
		
		Response response = given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .pathParam("username", userName)
		    .body(payload)
		
		.when()
		    .put(put_url);
		
		return response;
	}
	
	public static Response delete_user(String userName)
	{
		
		String delete_url = getURL().getString("delete_url");
		
		Response response = given()
		                        .pathParam("username", userName)
		
		.when()
		    .delete(delete_url);
		
		return response;
	}

}
