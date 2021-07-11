package com.example.Tab_Android.Tab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.Tab_Android.R;
import com.example.Tab_Android.loginAndSignUP.JoinActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArrivalActivity extends AppCompatActivity {
    String BASE_URL = "http://192.249.18.153:443/";
    private CommuteAPI commuteAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1_commute_map);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        commuteAPI = retrofit.create(CommuteAPI.class);

        createArrival();
        // getArrival();

    }
    private void getArrival() {
        Call<ArrivalData> call = commuteAPI.getArrival();

        call.enqueue(new Callback<ArrivalData>() {
            @Override
            public void onResponse(Call<ArrivalData> call, Response<ArrivalData> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(ArrivalActivity.this, "code: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrivalData arrivalResponse = response.body();
                Toast.makeText(ArrivalActivity.this, "call successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArrivalData> call, Throwable t) {
                Toast.makeText(ArrivalActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void createArrival() {
        ArrivalData arrivalData = new ArrivalData("yeeun", true, "---", "20210909");
        Call<ArrivalData> call = commuteAPI.createArrival(arrivalData);

        call.enqueue(new Callback<ArrivalData>() {
            @Override
            public void onResponse(Call<ArrivalData> call, Response<ArrivalData> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(ArrivalActivity.this, "code: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrivalData arrivalResponse = response.body();
                Toast.makeText(ArrivalActivity.this, "call successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArrivalData> call, Throwable t) {
                Toast.makeText(ArrivalActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

}