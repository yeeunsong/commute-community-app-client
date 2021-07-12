package com.example.Tab_Android.Tab1;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CommuteAPI {

    @POST("arrival")
    Call<ArrivalData> createArrival(@Body ArrivalData arrivalData);

    @POST("off")
    Call<OffData> createOff(@Body OffData offData);


}
