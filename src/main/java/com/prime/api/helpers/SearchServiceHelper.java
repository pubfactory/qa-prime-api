package com.prime.api.helpers;

import static io.restassured.RestAssured.given;

import com.prime.api.endpoints.SearchServiceEndpoint;
import com.prime.generics.APIUtils;
import com.prime.generics.BaseTest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SearchServiceHelper {

	private static String SEARCH_BASE = BaseTest.properties.getProperty("baseURI_Search");
	String SearchAPIendpoint;

	/**
	 * 
	 * This method takes hits the search results api response and return the
	 * response
	 * 
	 * @author Veena.Mathew
	 * @Created Date : 24/08/2023
	 */

	public Response fetchSearchResults(String platform, String site, String status) {

		APIUtils apiutils = new APIUtils();
		RequestSpecification request = apiutils.requestSpecification(SEARCH_BASE,
				SearchServiceEndpoint.FETCH_SEARCH_RESULTS, platform, site, status);
		RequestSpecification req = given().spec(request).log().all();
		ResponseSpecification res = apiutils.responseSpecification();
		Response response = req.when().get(SearchServiceEndpoint.FETCH_SEARCH_RESULTS).then().log().all().spec(res)
				.extract().response();
		return response;

	}

}
