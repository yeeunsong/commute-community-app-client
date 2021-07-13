package com.example.Tab_Android.Tab2;

import com.google.gson.annotations.SerializedName;

class DeletePostData{
    @SerializedName("postid")
    int postid;

    public DeletePostData(int postid) {
        this.postid = postid;
    }
}
