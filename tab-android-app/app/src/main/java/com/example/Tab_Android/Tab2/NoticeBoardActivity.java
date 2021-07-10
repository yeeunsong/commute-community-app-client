package com.example.Tab_Android.Tab2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Tab_Android.R;
import com.example.Tab_Android.RetrofitClient;
import com.example.Tab_Android.loginAndSignUP.LogSignServiceApi;
import com.example.Tab_Android.loginAndSignUP.LoginActivity;
import com.example.Tab_Android.loginAndSignUP.LoginResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeBoardActivity extends AppCompatActivity {
    Context context;
    private ServiceApi service;
    private RecyclerView listView; // 리스트뷰
    private ArrayList<TableData> cItemList = new ArrayList<>();
    RecyclerView.Adapter contentListAdapter;
    RecyclerView.LayoutManager layoutManager;
    JsonParser jsonParser = new JsonParser();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2_noticeboard);
        context = this.getBaseContext();
        service = RetrofitClient.getClient().create(ServiceApi.class);
        listView = (RecyclerView) findViewById(R.id.tableRecyclerView);
        listView.setHasFixedSize(true);
        // Set Layout Manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(layoutManager);

        contentListAdapter = new NoticeBoardAdapter(cItemList);

        listView.setAdapter(contentListAdapter); // 어댑터를 리스트뷰에 세팅
        init();
    }

    private void init(){
        GetPostData(new ShowPostData(0,getIntent().getStringExtra("nbname")));
    }

    private void GetPostData(ShowPostData data) {
        service.showpostlist(data).enqueue(new Callback<ShowPostResponse>() {
            @Override
            public void onResponse(Call<ShowPostResponse> call, Response<ShowPostResponse> response) {
                ShowPostResponse result = response.body();
                Toast.makeText(context, result.getresult().get(0).toString(), Toast.LENGTH_SHORT).show();
                JsonParser jsonParser = new JsonParser();

                //;
                /*
                JsonObject jsonobj = arrayToString(result.getresult());

                    String userid = jsonobj.get("userid").toString();
                    String title = jsonobj.get("title").toString();
                    String datetime = jsonobj.get("datetime").toString();

                   */
                contentListAdapter.notifyDataSetChanged();
                //finish();
            }

            @Override
            public void onFailure(Call<ShowPostResponse> call, Throwable t) {
                Toast.makeText(context, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());

            }
        });
    }

    public static <T> String arrayToString(ArrayList<T> list) {
        Gson g = new Gson();
        return g.toJson(list); }

    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr);
        //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner }


}
