//Step definition file for ReqRes API testing using Cucumber and RestAssured
//step definitions/ReqResStepDefinitions.java
package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import utility.ConfigLoader;
import utility.JsonReader;
import io.restassured.specification.RequestSpecification;

public class ReqResStepDefinitions {

	private Response response;
	private String authKey;
	private String payload; // Declare payload as a class-level variable
    private RequestSpecification requestSpec; // Declare a reusable RequestSpecification


	@Given("the ReqRes API is available")
	public void the_api_is_available() {
		RestAssured.baseURI = ConfigLoader.getProperty("baseURL");
		authKey = ConfigLoader.getProperty("x-api-key"); // Load the authorization key from config
		// Initialize the RequestSpecification with the common header
        requestSpec = RestAssured.given().header("x-api-key", authKey);
	}

	@Given("a registered user with valid email and password")
	public void a_registered_user_with_valid_email_and_password() {
		// This step can be used to set up any preconditions if needed
		// For this example, we assume the user is already registered
	}

	@When("a POST request is sent to {string}")
	public void send_post_request(String endpoint) {
		String payload = JsonReader.getPayload(endpoint);
		//response = RestAssured.given().contentType(ContentType.JSON).header("x-api-key", "reqres-free-v1").body(payload).post(endpoint); // Add x-api-key header
        response = requestSpec.contentType(ContentType.JSON).body(payload).post(endpoint);
																								
				
	}

	@Then("the response status should be {int}")
	public void verify_status_code(int statusCode) {
		response.then().statusCode(statusCode);
	}

	@Then("a token should be returned")
	public void verify_token_returned() {
		response.then().body("token", notNullValue());
	}

	@Given("a registered user with only email")
	public void a_registered_user_with_only_email() {
		// This step can be used to set up any preconditions if needed
		// For this example, we assume the user is already registered
	}

	@When("a POST request is sent to {string} with only email")
	public void send_post_request_with_only_email(String endpoint) {
		String payloadTemplate = JsonReader.getPayload(endpoint); // Fetch payload from JSON
		JsonObject jsonObject = new JsonParser().parse(payloadTemplate).getAsJsonObject();
		jsonObject.remove("password"); // Remove the password field
		payload = jsonObject.toString(); // Convert back to JSON string
		//response = RestAssured.given().contentType(ContentType.JSON).header("x-api-key", authKey).body(payload).post(endpoint);
		response = requestSpec.contentType(ContentType.JSON).body(payload).post(endpoint);

	}
	//And an error message should be returned write a step definition for this
	@Then("an error message should be returned")
	public void verify_error_message_returned() {
		response.then().body("error", notNullValue());
	}

	@Given("valid email and password")
	public void valid_email_and_password() {
		// This step can be used to set up any preconditions if needed
		// For this example, we assume the credentials are valid
	}

	@Given("only email is provided")
	public void only_email_is_provided() {
		// This step can be used to set up any preconditions if needed
		// For this example, we assume only email is provided
	}

	@When("a POST request is sent to {string} with email field")
	public void send_post_request_with_email_field(String endpoint) {

		String payloadTemplate = JsonReader.getPayload(endpoint); // Fetch payload from JSON
		JsonObject jsonObject = new JsonParser().parse(payloadTemplate).getAsJsonObject();
		jsonObject.remove("password"); // Remove the password field
		payload = jsonObject.toString(); // Convert back to JSON string
		//response = RestAssured.given().contentType(ContentType.JSON).header("x-api-key", authKey).body(payload).post(endpoint);
	    response = requestSpec.contentType(ContentType.JSON).body(payload).post(endpoint);
	}

	@When("a GET request is sent to {string}")
	public void send_get_request(String endpoint) {
		//response = RestAssured.given().header("x-api-key", "reqres-free-v1").get(endpoint); // Add x-api-key header
		response = requestSpec.get(endpoint);
	}

	@Then("a list of users should be returned")
	public void verify_user_list() {
		response.then().body("data", notNullValue());
	}

	// When a GET request is sent to "/api/users?page=999" invalid page
	@When("a GET request is sent to {string} invalid page")
	public void send_get_request_invalid_page(String endpoint) {
		//response = RestAssured.given().header("x-api-key", "reqres-free-v1").get(endpoint); // Add x-api-key header
		response = requestSpec.get(endpoint);		
	}

	@Then("an empty list or error should be returned")
	public void verify_empty_or_error() {
		response.then().body("data.size()", lessThanOrEqualTo(0));
	}

	@Given("user with ID {int} exists")
	public void user_with_id_exists(Integer id) {
		// This step can be used to set up any preconditions if needed
		// For this example, we assume the user with the given ID exists
	}

	@Then("user data should be returned")
	public void verify_user_data() {
		response.then().body("data", notNullValue());
	}

	// When a GET request is sent to "/api/users/999" invalid id
	@When("a GET request is sent to {string} invalid id")
	public void send_get_request_invalid_id(String endpoint) {
		//response = RestAssured.given().header("x-api-key", "reqres-free-v1").get(endpoint); // Add x-api-key header
		response = requestSpec.get(endpoint);		
	}

	@Given("name and job are provided")
	public void name_and_job_are_provided() {
		// This step can be used to set up any preconditions if needed
		// For this example, we assume name and job are provided

	}

	@Then("user should be created")
	public void verify_user_created() {
		response.then().body("id", notNullValue());
	}

	@Given("only job is provided")
	public void only_job_is_provided() {
		// This step can be used to set up any preconditions if needed
		// For this example, we assume only job is provided
	}

	@Then("the response status should be {int} or validation error")
	public void verify_status_code_or_validation_error(int statusCode) {
		response.then().statusCode(anyOf(equalTo(statusCode), equalTo(400)));
	}

	@Given("user with ID {int} exists and updated name and job are provided")
	public void user_with_id_exists_and_updated_name_and_job_are_provided(Integer id) {
		// For this example, we assume the user with the given ID exists and updated
	}

	@When("a PUT request is sent to {string} with name and job")
	public void send_put_request(String endpoint) {
		String payload = JsonReader.getPayload(endpoint);
		//response = RestAssured.given().contentType(ContentType.JSON).header("x-api-key", "reqres-free-v1").body(payload).put(endpoint); // Add x-api-key header
		response = requestSpec.contentType(ContentType.JSON).body(payload).put(endpoint);																									
				
	}

	@Then("updated data should be returned with name {string}")
	public void verify_updated_data_with_name(String expectedName) {
	    response.then().body("name", equalTo(expectedName));
	}


	@When("a PUT request is sent to {string}")
	public void send_put_request_to_update(String endpoint) {
		String payload = JsonReader.getPayload(endpoint);
		//response = RestAssured.given().contentType(ContentType.JSON).header("x-api-key", "reqres-free-v1").body(payload).put(endpoint); // Add x-api-key header
		response = requestSpec.contentType(ContentType.JSON).body(payload).put(endpoint);																									
				
	}

	@Then("the response status should be {int} or error")
	public void verify_status_code_or_error(int statusCode) {
		response.then().statusCode(anyOf(equalTo(statusCode), equalTo(404)));

	}

	@Given("user with ID {int} exists for deletion")
	public void user_with_id_exists_for_deletion(Integer id) {
		// This step can be used to set up any preconditions if needed
		// For this example, we assume the user with the given ID exists for deletion

	}

	@When("a DELETE request is sent to {string}")
	public void send_delete_request(String endpoint) {
		//response = RestAssured.given().header("x-api-key", "reqres-free-v1").delete(endpoint); // Add x-api-key header
		response = requestSpec.delete(endpoint);		
	}

	@Then("no content should be returned")
	public void verify_no_content() {
		response.then().body(equalTo(""));
	}

	@When("a DELETE request is sent to {string} invalid id")
	public void send_delete_request_invalid_id(String endpoint) {
		response = RestAssured.given().header("x-api-key", "reqres-free-v1").delete(endpoint); // Add x-api-key header
		response = requestSpec.delete(endpoint);		
	}

	@Then("the response status should be {int} or no content")
	public void verify_status_code_or_no_content(int statusCode) {
		response.then().statusCode(anyOf(equalTo(statusCode), equalTo(404)));

	}

	@Given("partial data for update is provided")
	public void partial_data_for_update_is_provided() {
		payload = "{ \"job\": \"zion resident\" }"; // Example partial data
	}

	@When("a PATCH request is sent to {string} with partial data")
	public void send_patch_request(String endpoint) {
		String payload = JsonReader.getPayload(endpoint);
		//response = RestAssured.given().header("x-api-key", "reqres-free-v1").contentType(ContentType.JSON).body(payload).patch(endpoint); // Add x-api-key header
		response = requestSpec.contentType(ContentType.JSON).body(payload).patch(endpoint);		
	}

	@Then("the response should contain the updated data")
	public void the_response_should_contain_the_updated_data() {
		response.then().body("job", equalTo("zion resident"));
	}
}