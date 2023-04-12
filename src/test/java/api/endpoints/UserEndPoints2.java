package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
/*
UserEndpoint.java
Created to perform CRUD operations request to an User API
 */

public class UserEndPoints2 {

//This method is created for getting URL's from properties file	
	static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");// Load properties file //routes is file name from
																	// property file
		return routes;
	}

	// User Payload --> will be passed from testcases datas
	public static Response createUser(User payload) {
		String post_url = getURL().getString("post_url");

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(post_url);
		return response;

	}

	// String userName --> will be passed from testcases data
	public static Response readUser(String userName) {

		String get_url = getURL().getString("get_url");

		Response response = given().pathParam("username", userName)

				.when()
				// this below method passed by username in the url ,which come from Routes class
				.get(get_url);
		return response;

	}

	// String userName --> will be passed from testcases data
	public static Response updateUser(String userName, User payload) {

		String update_url = getURL().getString("update_url");

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", userName).body(payload)

				.when().put(update_url);
		return response;
	}

	public static Response deleteUser(String userName) {

		String delete_url = getURL().getString("delete_url");

		Response response = given().pathParam("username", userName).when().delete(delete_url);
		return response;

	}
}
