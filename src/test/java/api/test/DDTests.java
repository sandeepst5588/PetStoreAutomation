package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUsers(String userID, String userName, String fName, String lName, String useremail, String pwd,
			String ph) {// The sequence of parameters must be in sequence Xl sheet

		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));// Takes string as i/p give int as o/p.
		userPayload.setUsername(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);

		Response response = UserEndPoints.createUser(userPayload);

		Assert.assertEquals(response.statusCode(), 200);

	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUsers(String userName) {

		Response response = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.statusCode(), 200);

	}

}
