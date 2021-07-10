package com.example.Tab_Android.Tab2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("showpost")
    Call<ShowPostResponse> showpostlist(@Body ShowPostData data);
}
