package com.prime.api.endpoints;

public interface LoginServiceEndpoint {
    String USER_AUTHENTICATE = "/{platform}/{site}/{status}/rest/authentication/v1/authenticate/active";
    String USER_TOKEN_RESOURCE = "/{platform}/{site}/{status}/rest/authentication/v1/uad-token/verify/{token}";
    String USER_CONTENT = null;

}
