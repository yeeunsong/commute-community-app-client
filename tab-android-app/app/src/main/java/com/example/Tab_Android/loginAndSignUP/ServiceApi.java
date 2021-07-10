package com.example.Tab_Android.loginAndSignUP;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("join")
    Call<JoinResponse> userJoin(@Body JoinData data);
}