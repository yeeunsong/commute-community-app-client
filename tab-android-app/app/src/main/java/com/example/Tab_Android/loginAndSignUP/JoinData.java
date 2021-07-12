package com.example.Tab_Android.loginAndSignUP;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("userName")
    private String userName;

    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("userPwd")
    private String userPwd;

    @SerializedName("userCompany")
    private String userCompany;

    public JoinData(String userName, String userEmail, String userPwd, String userCompany) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.userCompany = userCompany;
    }
}
