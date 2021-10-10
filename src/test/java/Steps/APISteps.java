package Steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import io.restassured.module.jsv.JsonSchemaValidator;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class APISteps {

    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;
    private JSONObject requestParams;

    @Given("^the base URI is set to (.+)")
    public void setBaseUri(String URI) {
        request = given()
                .baseUri(URI)
                .contentType(ContentType.JSON);
    }

    @Then("^a (\\d+) status code is received$")
    public void validateListOfUsers(int expectedStatusCode) {
        response.then().assertThat().statusCode(expectedStatusCode);
    }

    @Then("^(\\d+) items are present in the (.+) endpoint$")
    public void itemsArePresentInTheEndpoint(int expectedSize, String endpoint) {
        response = request
                .when()
                .get(endpoint);
        Assertions.assertEquals(response.getStatusCode(), 200);

        List<String> jsonResponse = response.jsonPath().getList("$");
        int actualSize = jsonResponse.size();
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedSize, actualSize)
        );
    }

    @Then("^(.+) is present in the response at (.+) endpoint$")
    public void validateItemInResponse(String expectedValue, String endpoint) {
        response = request
                .when()
                .get(endpoint);
        Assertions.assertEquals(response.getStatusCode(), 200);

        List<String> jsonResponse = response.jsonPath().getList("$");
        Assert.assertTrue(expectedValue + " is not present in list.", jsonResponse.toString()
                .contains(expectedValue));

    }

    @Given("^requestParams are needed to be sent to an endpoint$")
    public void aPOSTWantsToBeSentToTheEndpoint() {
        requestParams = new JSONObject();
    }

    @And("^add the param (.+) with value (.+)$")
    public void addTheParamWithValue(String param, String value) {
        requestParams.put(param, value);
    }

    @When("^the POST is sent to the (.+) endpoint$")
    public void thePOSTIsSentToTheUserEndpoint(String endpoint) {
        request.body(requestParams);
        response = request.post(endpoint);
    }

    @When("^the POST is sent to the (.+) endpoint with file body$")
    public void thePOSTIsSentToTheUserEndpointWithFileBody(String endpoint) {
        response = request.post(endpoint);
    }

    @Given("^a user (.+) is logged in with password (.+)$")
    public void aUserFishyIsLoggedInWithPassword(String user, String password) {
        String endpoint = "/user/login?username=#{user}&password=#{pass}%20";
        String userReplacedEndpoint = endpoint.replace("#{user}", user);
        String fullEndpoint = userReplacedEndpoint.replace("#{pass}", password);
        response = request.when().get(fullEndpoint);
    }

    @And("^the body is loaded from (.+)$")
    public void theBodyIsLoadedFromThis(String path) {
        File jsonBodyFile = new File(path);
        request.body(jsonBodyFile);

    }

    @And("^the schema is correct according to schema (.+)$")
    public void theSchemaIsCorrectAccordingToSchemaX(String file) {
        response.then().assertThat().body(matchesJsonSchemaInClasspath("newPetSchema.json"));

    }
}
