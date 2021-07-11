package com.example.Tab_Android;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
<<<<<<< HEAD:tab-android-app/app/src/main/java/com/example/Tab_Android/RetrofitClient.java
    private final static String BASE_URL = "http://192.249.18.153:443/";
=======
    private final static String BASE_URL = "http://192.249.18.153:80/";
>>>>>>> 783005024f0fcb5eb6412153ed053341cfdae75a:tab-android-app/app/src/main/java/com/example/Tab_Android/loginAndSignUP/RetrofitClient.java
    private static Retrofit retrofit = null;

    private RetrofitClient() {
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
