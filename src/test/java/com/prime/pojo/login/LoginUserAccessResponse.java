package com.prime.pojo.login;
import java.util.List;

import com.google.gson.annotations.SerializedName;

   
public class LoginUserAccessResponse {

   @SerializedName("accounts")
   List<Accounts> accounts;

   @SerializedName("userAccessDescriptor")
   String userAccessDescriptor;

   @SerializedName("uadTimeoutSeconds")
   int uadTimeoutSeconds;


    public void setAccounts(List<Accounts> accounts) {
        this.accounts = accounts;
    }
    public List<Accounts> getAccounts() {
        return accounts;
    }
    
    public void setUserAccessDescriptor(String userAccessDescriptor) {
        this.userAccessDescriptor = userAccessDescriptor;
    }
    public String getUserAccessDescriptor() {
        return userAccessDescriptor;
    }
    
    public void setUadTimeoutSeconds(int uadTimeoutSeconds) {
        this.uadTimeoutSeconds = uadTimeoutSeconds;
    }
    public int getUadTimeoutSeconds() {
        return uadTimeoutSeconds;
    }
    
}