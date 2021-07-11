package com.example.Tab_Android.Tab2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ShowPostResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("result")
    private ArrayList result;

    public int getCode() {
        return code;
    }
    public ArrayList getresult() {
        return result;
    }

}
