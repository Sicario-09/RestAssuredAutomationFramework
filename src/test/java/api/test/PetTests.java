package api.test;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndPoints;
import api.payload.PetPOJO;
import io.restassured.response.Response;

public class PetTests {
	
	Faker faker;
	PetPOJO payload;
	
	public Logger logger;

	@BeforeClass
	public void setUp() {

		faker = new Faker();

		payload = new PetPOJO();

		payload.setId(faker.number().randomDigit());            //.setId(faker.idNumber().hashCode())
		payload.setName(faker.name().firstName());
		
		// Logs
		
	//	logger = LogManager.getLogger(this.getClass());

	}

	@Test(priority = 1)
	public void testPostPetById() {

		Response response = PetEndPoints.create_pet(payload);

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 2)
	public void testGetPetById() {

		Response response = PetEndPoints.read_pet(this.payload.getId());

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3)
	public void testUpdatePetById() {
		// Update data using payload
		payload.setName(faker.name().firstName());
		

		Response response = PetEndPoints.update_pet(this.payload.getId(), payload);

	//	response.then().log().body().statusCode(200); // chai assertions

	//	Assert.assertEquals(response.getStatusCode(), 200); // TestNG assertions

		// checking data after updation

		Response responseAfterUpdate = PetEndPoints.read_pet(this.payload.getId());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		responseAfterUpdate.then().log().body();

	}

	@Test(priority = 4)
	public void testDeletePetById() {

		Response response = PetEndPoints.delete_pet(this.payload.getId());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
