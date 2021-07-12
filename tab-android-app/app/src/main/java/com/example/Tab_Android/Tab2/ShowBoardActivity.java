package com.example.Tab_Android.Tab2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Tab_Android.R;
import com.example.Tab_Android.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowBoardActivity extends AppCompatActivity {
    Context context;
    private ServiceApi service;
    private RecyclerView listView; // 리스트뷰
    private ArrayList<BoardData> cItemList = new ArrayList<>();
    ShowBoardAdapter contentListAdapter;
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

        contentListAdapter = new ShowBoardAdapter(cItemList);
        listView.setAdapter(contentListAdapter); // 어댑터를 리스트뷰에 세팅

        contentListAdapter.setOnItemClickListener(new ShowBoardAdapter.OnItemClickListener() {
            @Override
            public void onClick(int postid) {
                //System.out.println("postid"+postid);
                Intent intent = new Intent(context, PostActivity.class)
                        .putExtra("postid",postid);
                startActivityForResult(intent,1);
            }
        });
        
        init();
    }

    private void init(){
        GetPostData(new ShowBoardData(0,getIntent().getStringExtra("nbname")));
    }

    private void GetPostData(ShowBoardData data) {
        service.showBoardlist(data).enqueue(new Callback<ShowBoardResponse>() {
            @Override
            public void onResponse(Call<ShowBoardResponse> call, Response<ShowBoardResponse> response) {
                ShowBoardResponse result = response.body();
                //Toast.makeText(context, result.getresult().get(0).getClass().getName(), Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                for(int i=0;i<result.getresult().size();i++){
                    JsonObject jsonObject = gson.toJsonTree(result.getresult().get(i)).getAsJsonObject();
                    int postid = (int) Float.parseFloat(jsonObject.get("postid").toString());
                    String userid = jsonObject.get("userid").toString();
                    String title = jsonObject.get("title").toString();
                    String datetime = jsonObject.get("datetime").toString();
                    cItemList.add(new BoardData(postid,userid,title,datetime));
                }

                contentListAdapter.notifyDataSetChanged();
                //finish();
            }

            @Override
            public void onFailure(Call<ShowBoardResponse> call, Throwable t) {
                Toast.makeText(context, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());

            }
        });
    }
}
