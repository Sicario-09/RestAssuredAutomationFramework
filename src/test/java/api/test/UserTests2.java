package api.test;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.UserPOJO;
import groovyjarjarantlr4.v4.runtime.misc.LogManager;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	UserPOJO payload;
	
	public Logger logger;

	@BeforeClass
	public void setUp() {

		faker = new Faker();

		payload = new UserPOJO();

		payload.setId(faker.idNumber().hashCode());
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		payload.setPassword(faker.internet().password(5, 10));
		payload.setPhone(faker.phoneNumber().cellPhone());
		
		// Logs
		
	//	logger = LogManager.getLogger(this.getClass());

	}

	@Test(priority = 1)
	public void testPostUser() {

		Response response = UserEndPoints2.create_user(payload);

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 2)
	public void testGetUserByName() {

		Response response = UserEndPoints2.read_user(this.payload.getUsername());

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3)
	public void testUpdateUserByName() {
		// Update data using payload
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints2.update_user(this.payload.getUsername(), payload);

		response.then().log().body().statusCode(200); // chai assertions

		Assert.assertEquals(response.getStatusCode(), 200); // TestNG assertions

		// checking data after updation

		Response responseAfterUpdate = UserEndPoints2.read_user(this.payload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		responseAfterUpdate.then().log().body();

	}

	@Test(priority = 4)
	public void testDeleteUserByName() {

		Response response = UserEndPoints2.delete_user(this.payload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
