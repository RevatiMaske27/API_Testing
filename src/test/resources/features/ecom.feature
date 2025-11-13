Feature: ReqRes API Testing

  Background:
    Given the ReqRes API is available

  Scenario: Login with valid credentials
    Given a registered user with valid email and password
    When a POST request is sent to "<login_endpoint>"
    Then the response status should be 200
    And a token should be returned

  Scenario: Login with missing password
    Given a registered user with only email
    When a POST request is sent to "<login_endpoint>" with only email 
    Then the response status should be 400
    And an error message should be returned

  Scenario: Register with valid data
    Given valid email and password
    When a POST request is sent to "<register_endpoint>"
    Then the response status should be 200
    And a token should be returned

  Scenario: Register with missing password
    Given only email is provided
    When a POST request is sent to "<register_endpoint>" with email field
    Then the response status should be 400
    And an error message should be returned

  Scenario: List users on page 2
    When a GET request is sent to "<users_endpoint>?page=2"
    Then the response status should be 200
    And a list of users should be returned

  Scenario: List users with invalid page
    When a GET request is sent to "<users_endpoint>?page=999" invalid page
    Then the response status should be 200
    And an empty list or error should be returned

  Scenario: Get user by valid ID
    Given user with ID 2 exists
    When a GET request is sent to "<users_endpoint>/2"
    Then the response status should be 200
    And user data should be returned

  Scenario: Get user by invalid ID
    When a GET request is sent to "<users_endpoint>/999" invalid id
    Then the response status should be 404

  Scenario: Create user with valid data
    Given name and job are provided
    When a POST request is sent to "<users_endpoint>"
    Then the response status should be 201
    And user should be created

  Scenario: Create user with missing name
    Given only job is provided
    When a POST request is sent to "<users_endpoint>"
    Then the response status should be 400 or validation error

  Scenario: Update user with valid data
    Given user with ID 2 exists and updated name and job are provided
    When a PUT request is sent to "<users_endpoint>/2"
    Then the response status should be 200
    And updated data should be returned with name "neo"


  Scenario: Update user with invalid ID
    When a PUT request is sent to "<users_endpoint>/999" with name and job
    Then the response status should be 404 or error
    
	Scenario: Update user data with partial information
    Given user with ID 2 exists
    And partial data for update is provided
    When a PATCH request is sent to "<users_endpoint>/2" with partial data
    Then the response status should be 200
    And the response should contain the updated data

  Scenario: Delete user by valid ID
    Given user with ID 2 exists for deletion
    When a DELETE request is sent to "<users_endpoint>/2"
    Then the response status should be 204
    And no content should be returned

  Scenario: Delete user by invalid ID
    When a DELETE request is sent to "<users_endpoint>/999"
    Then the response status should be 404 or no content