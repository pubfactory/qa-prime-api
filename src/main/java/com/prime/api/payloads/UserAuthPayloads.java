package com.prime.api.payloads;

public class UserAuthPayloads {

	/**
	 * * This method returns user creds needed for authentication api response
	 * 
	 * @return: Response
	 * @author Veena.Mathew
	 * @Created Date : 24/08/2023
	 */

	public static String userAuthentcationPayload(String username, String password) {
		String creds = "{\r\n" + "	\"username\":\"" + username + "\",\r\n" + "	\"password\":\"" + password + "\"\r\n"
				+ "}";
		return creds;

	}

}
