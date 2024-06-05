package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.PetPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndPoints {

	
	public static Response create_pet(PetPOJO pet)
	{
		
		Response response = given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .body(pet)
		
		.when()
		    .post(Routes.pet_post_url);
		
		return response;
	}
	
	public static Response read_pet(int petID)
	{
		
		Response response = given()
		                        .pathParam("petID", petID)
		
		.when()
		    .get(Routes.pet_get_url);
		
		return response;
	}
	
	public static Response update_pet(int petID, PetPOJO pet)
	{
		
		Response response = given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .pathParam("petID", petID)
		    .body(pet)
		
		.when()
		    .put(Routes.pet_put_url);
		
		return response;
	}
	
	public static Response delete_pet(int petID)
	{
		
		Response response = given()
		                        .pathParam("petID", petID)
		
		.when()
		    .delete(Routes.pet_delete_url);
		
		return response;
	}

}

