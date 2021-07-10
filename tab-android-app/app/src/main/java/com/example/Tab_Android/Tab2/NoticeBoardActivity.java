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


import java.util.ArrayList;

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
                Toast.makeText(context, result.getResult().toString(), Toast.LENGTH_SHORT).show();

                finish();
            }

            @Override
            public void onFailure(Call<ShowPostResponse> call, Throwable t) {
                Toast.makeText(context, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());

            }
        });
    }

}
