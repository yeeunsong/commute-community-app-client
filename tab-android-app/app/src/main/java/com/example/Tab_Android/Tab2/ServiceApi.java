package com.example.Tab_Android.Tab2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("showboard")
    Call<ShowBoardResponse> showBoardlist(@Body ShowBoardData data);
    @POST("showpost")
    Call<ShowPostResponse> showPost(@Body ShowPostData data);
    @POST("writepost")
    Call<WritePostResponse> writePost(@Body WritePostData data);
    @POST("deletepost")
    Call<DeletePostResponse> deletePost(@Body DeletePostData data);
    @POST("writecomment")
    Call<WriteCommentResponse> writeComment(@Body WriteCommentData data);
    @POST("getcomment")
    Call<ShowCommentResponse> getComment(@Body ShowCommentData data);


    class DeletePostData{
        @SerializedName("postid")
        int postid;

        public DeletePostData(int postid) {
            this.postid = postid;
        }
    }

    class DeletePostResponse{
        @SerializedName("code")
        private int code;

        public int getCode() {
            return code;
        }
    }
}

