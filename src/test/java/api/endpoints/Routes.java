package api.endpoints;

/*
 Swagger URI--->https://petstore.swagger.io
 Create User (post):https://petstore.swagger.io/v2/user
 Get User (post):https://petstore.swagger.io/v2/user/{username}
 Update User (put):https://petstore.swagger.io/v2/user/{username}
 Delete User (Delete):https://petstore.swagger.io/v2/user/{username}
 
  */

public class Routes {

	public static String base_url = "https://petstore.swagger.io/v2";

	// user module
	public static String post_url = base_url + "/user";
	public static String get_url = base_url + "/user/{username}";
	public static String update_url = base_url + "/user/{username}";
	public static String delete_url = base_url + "/user/{username}";

	// Store module
	// Here we will store module URL's

	// PetStore module
	// Here we will Pet module URL's

}
