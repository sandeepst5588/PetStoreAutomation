package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
/*
UserEndpoint.java
Created to perform CRUD operations request to an User API
 */

public class UserEndPoints {
	// User Payload --> will be passed from testcases datas
	public static Response createUser(User payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(Routes.post_url);
		return response;

	}

	// String userName --> will be passed from testcases data
	public static Response readUser(String userName) {
		Response response = given().pathParam("username", userName)

				.when()
				// this below method passed by username in the url ,which come from Routes class
				.get(Routes.get_url);
		return response;

	}

	// String userName --> will be passed from testcases data
	public static Response updateUser(String userName, User payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", userName).body(payload)

				.when().put(Routes.update_url);
		return response;
	}

	public static Response deleteUser(String userName) {
		Response response = given().pathParam("username", userName).when().delete(Routes.delete_url);
		return response;

	}
}
