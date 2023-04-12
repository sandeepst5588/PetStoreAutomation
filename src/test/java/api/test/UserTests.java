package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;

	public Logger logger;

	// using POJO
	@BeforeClass
	public void setUp() {

		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		// logs
		logger = LogManager.getLogger(this.getClass());

	}

	@Test(priority = 1)
	public void testPostUser() {

		logger.info("*************** Creating a User******************");
		Response response = UserEndPoints.createUser(userPayload);

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************** User is Created******************");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		logger.info("*************** Reading user info******************");
		Response response = UserEndPoints.readUser(userPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***************User info is displayed ******************");

	}

	@Test(priority = 3)
	public void testUpdateUserByName() {

		// Updatig data using payload
		logger.info("*************** Updating User******************");

		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints.updateUser(userPayload.getUsername(), userPayload);
		response.then().log().body();
		// response.then().log().body().statusCode(200);// This is chai Asseration comes
		// along with REST Assured API

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************** User is updated ******************");
		// Checking data after updation
		Response responseAfterUpdate = UserEndPoints.readUser(userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

	}

	@Test(priority = 4)
	public void testDeleteUserByName() {
		logger.info("*************** Deleting a User******************");
		Response response = UserEndPoints.deleteUser(userPayload.getUsername());

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************** Deleted User******************");
	}

}
