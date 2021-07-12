package com.example.Tab_Android.loginAndSignUP;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("userId")
    private String userId;

    @SerializedName("userCompany")
    private String userCompany;

    @SerializedName("userEmail")
    private String userEmail;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserCompany() {
        return userCompany;
    }

    public String getUserEmail(){
        return userEmail;
    }
}
