package com.example.Tab_Android.loginAndSignUP;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LogSignServiceApi {
    @POST("user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);
    @POST("user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);
    @GET("user/logout")
    Call<LogoutResponse> userLogout();

}