package com.prime.api.helpers;

import static io.restassured.RestAssured.given;

import com.prime.api.endpoints.LoginServiceEndpoint;
import com.prime.api.payloads.UserAuthPayloads;
import com.prime.generics.APIUtils;
import com.prime.generics.BaseTest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class LoginServiceHelper {
	// want the below to point to a base
	private static String AUTH_BASE = BaseTest.properties.getProperty("baseURI_Authentication");

	/**
	 * 
	 * This method takes hits the authenticate api response and return the JWT Token
	 * for an user
	 * 
	 * @author Veena.Mathew
	 * @Created Date : 28/08/2023
	 */

	public Response fetchUserAccessDescriptionToken(String platform, String site, String status, String username,
			String password) {
		// System.out.println("INSIDE" + site + platform + status);
		APIUtils apiutils = new APIUtils();
		RequestSpecification request = apiutils.requestSpecification(AUTH_BASE, LoginServiceEndpoint.USER_AUTHENTICATE,
				platform, site, status);
		String requestBody = UserAuthPayloads.userAuthentcationPayload(username, password);
		RequestSpecification req = given().spec(request).body(requestBody).log().all();
		ResponseSpecification res = apiutils.responseSpecification();
		Response response = req.when().post(LoginServiceEndpoint.USER_AUTHENTICATE).then().log().all().spec(res)
				.extract().response();
		return response;

	}

	public Response fetchUserInfoUsingToken(String platform, String site, String status, String userToken) {
		// System.out.println("INSIDE" + site + platform + status);
		APIUtils apiutils = new APIUtils();
		RequestSpecification request = apiutils
				.requestSpecification(AUTH_BASE, LoginServiceEndpoint.USER_TOKEN_RESOURCE, platform, site, status)
				.pathParam("token", userToken);
		// String requestBody = UserAuthPayloads.userAuthentcationPayload(username,
		// password);
		RequestSpecification req = given().spec(request).log().all();
		ResponseSpecification res = apiutils.responseSpecification();
		Response response = req.when().get(LoginServiceEndpoint.USER_TOKEN_RESOURCE).then().log().all().spec(res)
				.extract().response();
		return response;

	}

}
