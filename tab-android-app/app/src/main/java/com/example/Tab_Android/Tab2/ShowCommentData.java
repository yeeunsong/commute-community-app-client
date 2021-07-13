package com.example.Tab_Android.Tab2;

import com.google.gson.annotations.SerializedName;

public class ShowCommentData {
   @SerializedName("postid")
   int postid;
   public ShowCommentData(int postid){
       this.postid = postid;
   }
}
