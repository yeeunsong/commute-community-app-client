package com.example.Tab_Android.Tab2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("showboard")
    Call<ShowBoardResponse> showBoardlist(@Body ShowBoardData data);
    @POST("showpost")
    Call<ShowPostResponse> showPost(@Body ShowPostData data);
}
