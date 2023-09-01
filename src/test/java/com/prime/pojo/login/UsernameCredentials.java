package com.prime.pojo.login;

import com.google.gson.annotations.SerializedName;

   
public class UsernameCredentials {

   @SerializedName("username")
   String username;


    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    
}