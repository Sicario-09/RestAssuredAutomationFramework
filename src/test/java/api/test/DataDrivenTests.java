package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.UserPOJO;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String UserID, String UserName, String FirstName, String LastName, String Email, String Password, String Phone) 
	{

		UserPOJO payload = new UserPOJO();
		
		payload.setId(Integer.parseInt(UserID));
		payload.setUsername(UserName);
		payload.setFirstName(FirstName);
		payload.setLastName(LastName);
		payload.setEmail(Email);
		payload.setPassword(Password);
		payload.setPhone(Phone);
		
		Response response = UserEndPoints.create_user(payload);

		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String UserName)
	{
		Response response = UserEndPoints.delete_user(UserName);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
