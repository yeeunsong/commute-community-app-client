package com.example.Tab_Android.Tab2;

import com.google.gson.annotations.SerializedName;

public class ShowPostData {
    @SerializedName("postid")
    int postid;

    public ShowPostData(int postid) {
        this.postid = postid;
    }
}
