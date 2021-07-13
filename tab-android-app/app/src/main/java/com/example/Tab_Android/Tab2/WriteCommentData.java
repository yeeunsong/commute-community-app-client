package com.example.Tab_Android.Tab2;

import com.google.gson.annotations.SerializedName;

public class WriteCommentData {
    @SerializedName("postid")
    int postid;
    @SerializedName("name")
    String name;
    @SerializedName("content")
    String content;
    @SerializedName("anonymous")
    boolean anonymous;

    public WriteCommentData(int postid,String name, String content, boolean anonymous) {
        this.postid = postid;
        this.name = name;
        this.content = content;
        this.anonymous = anonymous;
    }

}


