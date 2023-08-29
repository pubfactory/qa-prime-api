package com.prime.api.helpers;

import static io.restassured.RestAssured.given;

import com.prime.generics.BaseTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestDataFetcherAPIHelper {

	private static String CONTENT_BASE = BaseTest.properties.getProperty("baseURI_Content");

	/**
	 * 
	 * This method takes hits the smoke test data for content api and return the
	 * response
	 * 
	 * @return: Response
	 * @author Veena.Mathew
	 * @Created Date : 24/08/2023
	 */
	public static Response getTestData() {
		System.out.println("CONTENT_BASE=" + CONTENT_BASE);
		RestAssured.baseURI = CONTENT_BASE;
		Response response = given().when().get("/q/smoketest-data").then().log().all().assertThat().statusCode(200)
				.extract().response();
		return response;

	}
}
