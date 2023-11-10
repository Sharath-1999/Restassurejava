package Restpack;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Updateapi {

	
		// TODO Auto-generated method stub
		@Test
		public  void  updateplace() {
			// TODO Auto-generated method stub

			//Update Add Place API is working as expected 
			//when - give inputs 
			//given - submit api
			//then - validate the response
			// getting some errors
			
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
	
	String newAdress="70 Summer walk, USA";
	
	String response1=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	.body("{\r\n"
			+ "\"place_id\":\""+placeId+"\",\r\n"
			+ "\"address\":\""+newAdress+"\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}\r\n"
			+ "")
	.when().put("/maps/api/place/update/json")
	.then().assertThat().log().all().statusCode(200).extract().response().asString();
	System.out.println(response1);
	//Get place
	
	String getPlaceResponse=given().log().all().queryParam("key","qaclick123")
	.queryParam("place_id", placeId)
	.when().get("/maps/api/place/get/json")
	.then().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath js1=new JsonPath(getPlaceResponse);
	String actualaddress=js1.getString("address");
	System.out.println(actualaddress +"is the actual address");
	Assert.assertEquals(actualaddress, newAdress);
		}
	}

