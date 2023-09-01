package com.prime.pojo.login;
import java.util.List;

import com.google.gson.annotations.SerializedName;

   
public class Accounts {

   @SerializedName("firstName")
   String firstName;

   @SerializedName("lastName")
   String lastName;

   @SerializedName("email")
   String email;

   @SerializedName("usernameCredentials")
   UsernameCredentials usernameCredentials;

   @SerializedName("id")
   int id;

   @SerializedName("name")
   String name;

   @SerializedName("accountType")
   String accountType;

   @SerializedName("communal")
   boolean communal;

   @SerializedName("consortiumIds")
   List<String> consortiumIds;


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    
    public void setUsernameCredentials(UsernameCredentials usernameCredentials) {
        this.usernameCredentials = usernameCredentials;
    }
    public UsernameCredentials getUsernameCredentials() {
        return usernameCredentials;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public String getAccountType() {
        return accountType;
    }
    
    public void setCommunal(boolean communal) {
        this.communal = communal;
    }
    public boolean getCommunal() {
        return communal;
    }
    
    public void setConsortiumIds(List<String> consortiumIds) {
        this.consortiumIds = consortiumIds;
    }
    public List<String> getConsortiumIds() {
        return consortiumIds;
    }
    
}