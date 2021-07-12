package com.example.Tab_Android.Tab2;

import com.google.gson.annotations.SerializedName;

public class WritePostData {
    @SerializedName("username")
    String username;
    @SerializedName("content")
    String postcontent;
    @SerializedName("title")
    String title;
    @SerializedName("anonymous")
    boolean anonymous;
    //image need

    public WritePostData(String username,String content, String title, boolean anonymous) {
        this.username = username;
        this.postcontent = content;
        this.title = title;
        this.anonymous = anonymous;
    }


}
