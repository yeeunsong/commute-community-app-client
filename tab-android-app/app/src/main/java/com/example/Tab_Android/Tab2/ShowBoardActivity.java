package com.example.Tab_Android.Tab2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.Tab_Android.R;
import com.example.Tab_Android.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowBoardActivity extends AppCompatActivity {
    Context context;
    private ServiceApi service;
    private RecyclerView listView; // 리스트뷰
    private ArrayList<BoardData> cItemList = new ArrayList<>();
    ShowBoardAdapter contentListAdapter;
    private FloatingActionButton fab_writingpost;
    RecyclerView.LayoutManager layoutManager;
    SimpleDateFormat sqlformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2_noticeboard);


        String nbname = getIntent().getExtras().getString("nbname");
        context = this.getBaseContext();
        service = RetrofitClient.getClient().create(ServiceApi.class);
        listView = (RecyclerView) findViewById(R.id.tableRecyclerView);
        listView.setHasFixedSize(true);
        // Set Layout Manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(layoutManager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(nbname);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*Toolbar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/

        contentListAdapter = new ShowBoardAdapter(cItemList);
        listView.setAdapter(contentListAdapter); // 어댑터를 리스트뷰에 세팅

        SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                init();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        contentListAdapter.setOnItemClickListener(new ShowBoardAdapter.OnItemClickListener() {
            @Override
            public void onClick(int postid) {
                //System.out.println("postid"+postid);
                Intent intent = new Intent(context, ShowPostActivity.class)
                        .putExtra("postid", postid);
                startActivityForResult(intent, 2);
            }
        });
        fab_writingpost = (FloatingActionButton) findViewById(R.id.fab_writingpost);

        fab_writingpost.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WritePostActivity.class)
                        .putExtra("nbname",getIntent().getStringExtra("nbname"));;
                startActivityForResult(intent, 2);
            }
        });

        init();
    }

    private void init() {
        cItemList.clear();
        //System.out.println("tester: "+getIntent().getStringExtra("nbname"));
        GetPostData(new ShowBoardData(0, getIntent().getStringExtra("nbname")));
    }

    private void GetPostData(ShowBoardData data) {
        service.showBoardlist(data).enqueue(new Callback<ShowBoardResponse>() {
            @Override
            public void onResponse(Call<ShowBoardResponse> call, Response<ShowBoardResponse> response) {
                ShowBoardResponse result = response.body();
                //Toast.makeText(context, result.getresult().get(0).getClass().getName(), Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                for (int i = 0; i < result.getresult().size(); i++) {
                    JsonObject jsonObject = gson.toJsonTree(result.getresult().get(i)).getAsJsonObject();
                    int postid = (int) Float.parseFloat(jsonObject.get("postid").toString());
                    String userid = jsonObject.get("userid").toString();
                    String title = jsonObject.get("title").toString();
                    Date mDate = new Date(System.currentTimeMillis());
                    String target = jsonObject.get("datetime").toString().replace("\"","");

                    String datetime =  CalculateTime.caltime(sqlformat,target,sqlformat.format(mDate));
                    System.out.println(jsonObject.get("datetime").toString()+sqlformat.format(mDate));
                    cItemList.add(new BoardData(postid, userid, title, datetime));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {//writing psot
            if (resultCode != Activity.RESULT_OK) {
                return;
            }
            //System.out.println("이봐 초기화가 안 된거 같은데?");
            init();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.boardactionbar, menu);

        return true;
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수

    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                break;
            }
            case R.id.action_search:{
                break;
            }
            case R.id.onlypostbyme:{
                break;
            }
            case R.id.writepost:{
                Intent intent = new Intent(context, WritePostActivity.class)
                        .putExtra("nbname",getIntent().getStringExtra("nbname"));
                startActivityForResult(intent, 2);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}