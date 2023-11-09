package Restpack;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;
public class Basicapi {
	@Test
	public  void  basic() {
		// TODO Auto-generated method stub

		//Validate if Add Place API is working as expected 
		//when - give inputs 
		//given - submit api
		//then - validate the response
		
	RestAssured.baseURI="https://rahulshettyacademy.com";
	given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	.body("{\r\n"
			+ "  \"location\": {\r\n"
			+ "    \"lat\": -38.383494,\r\n"
			+ "    \"lng\": 33.427362\r\n"
			+ "  },\r\n"
			+ "  \"accuracy\": 50,\r\n"
			+ "  \"name\": \"Frontline house\",\r\n"
			+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
			+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
			+ "  \"types\": [\r\n"
			+ "    \"shoe park\",\r\n"
			+ "    \"shop\"\r\n"
			+ "  ],\r\n"
			+ "  \"website\": \"http://google.com\",\r\n"
			+ "  \"language\": \"French-IN\"\r\n"
			+ "}\r\n"
			+ "")
	.when().post("/maps/api/place/add/json")
	.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"));
	//.header("server","Apache/2.4.18 (Ubuntu)");
	}

	
	@Test
	public  void  basicpost() {
		// TODO Auto-generated method stub

		//Update Add Place API is working as expected 
		//when - give inputs 
		//given - submit api
		//then - validate the response
		
	RestAssured.baseURI="https://rahulshettyacademy.com";
	String response= given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	.body("{\r\n"
			+ "  \"location\": {\r\n"
			+ "    \"lat\": -38.383494,\r\n"
			+ "    \"lng\": 33.427362\r\n"
			+ "  },\r\n"
			+ "  \"accuracy\": 50,\r\n"
			+ "  \"name\": \"Frontline house\",\r\n"
			+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
			+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
			+ "  \"types\": [\r\n"
			+ "    \"shoe park\",\r\n"
			+ "    \"shop\"\r\n"
			+ "  ],\r\n"
			+ "  \"website\": \"http://google.com\",\r\n"
			+ "  \"language\": \"French-IN\"\r\n"
			+ "}\r\n"
			+ "")
	.when().post("/maps/api/place/add/json")
	.then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
	//  .header("server","Apache/2.4.18 (Ubuntu)");
	System.out.println(response);
	JsonPath js=new JsonPath(response);// for parsing json
	String placeId=js.getString("place_id");
    System.out.println(placeId);	
	}
}
