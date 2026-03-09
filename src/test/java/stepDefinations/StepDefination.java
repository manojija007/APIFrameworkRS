package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{

	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	String place_ID;
	JsonPath js ;
	static String place_id;
	
	TestDataBuild data = new TestDataBuild();
		
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {					
						
		res = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
		
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resources, String methodType) {
	   
		APIResources resourceAPI = APIResources.valueOf(resources);
		System.out.println(resourceAPI.getResources());
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(methodType.equalsIgnoreCase("post")) {
			response = res.when().post(resourceAPI.getResources());
		}
		else if(methodType.equalsIgnoreCase("get")) {
			response = res.when().get(resourceAPI.getResources());
		}
		
		//response = res.when().post(resourceAPI.getResources()).then().assertThat().spec(resspec).extract().response();
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	  
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String KeyValue, String expectedValue) {	   
		
		Assert.assertEquals(getJsonPath(response,KeyValue),expectedValue);
		
		
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resources) throws IOException {
		
		place_id = getJsonPath(response,"place_id");
		res = given().spec(requestSpecification().queryParam("place_id",place_id));
		user_calls_with_http_request(resources,"GET");
		String actualName = getJsonPath(response,"name");
		Assert.assertEquals(expectedName, actualName);
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	   
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}

	


}
