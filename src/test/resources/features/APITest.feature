@PETSTORE
Feature: Pet store API

  Background:
    Given the base URI is set to https://petstore.swagger.io/v2


  @POST
  Scenario: Create new user
    Given requestParams are needed to be sent to an endpoint
    And add the param id with value 1
    And add the param username with value fishy
    And add the param firstName with value fstName
    And add the param lastName with value lstName
    And add the param email with value email@myemail.com
    And add the param password with value 1234
    And add the param phone with value 123456987654
    And add the param userStatus with value 1
    When the POST is sent to the /user endpoint
    Then a 200 status code is received

  @GET
  Scenario: Correct login scenario
    Given a user fishy is logged in with password 1234
    Then a 200 status code is received


  @POST @API
  Scenario: Post a new pet
    Given requestParams are needed to be sent to an endpoint
    And the body is loaded from src/test/resources/jsons/newPet.json
    When the POST is sent to the /pet endpoint with file body
    Then a 200 status code is received
    And the schema is correct according to schema newPetSchema.json