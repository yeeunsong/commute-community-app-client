package com.example.Tab_Android.Tab2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

public class ShowPostActivity extends AppCompatActivity {
    Context context;
    TextView titleview, nameview, dateview, contentview;

    private ServiceApi service;
    private RecyclerView commentRecyclerView; // 리스트뷰
    private ArrayList<CommentData> cItemList = new ArrayList<>();
    ShowCommentAdapter commentAdapter;
    RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2_postboard);
        context = this.getBaseContext();
        service = RetrofitClient.getClient().create(ServiceApi.class);

        titleview = (TextView)findViewById(R.id.posttitle);
        nameview = (TextView)findViewById(R.id.postname);
        dateview = (TextView)findViewById(R.id.postdate);
        contentview = (TextView)findViewById(R.id.postcontent);

        Toolbar toolbar = (Toolbar) findViewById(R.id.posttoolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        commentRecyclerView = (RecyclerView) findViewById(R.id.commentrecyclerView);
        commentRecyclerView.setHasFixedSize(true);
        // Set Layout Manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        commentRecyclerView.setLayoutManager(layoutManager);

        commentAdapter = new ShowCommentAdapter(cItemList);

        commentRecyclerView.setAdapter(commentAdapter); // 어댑터를 리스트뷰에 세팅

        init();
    }

    private void init(){
        //System.out.println(getIntent().getExtras().getInt("postid"));
        GetPostData(new ShowPostData(getIntent().getExtras().getInt("postid")));
        GetComment(new ShowCommentData(getIntent().getExtras().getInt("postid")));
    }

    private void GetPostData(ShowPostData data) {
        service.showPost(data).enqueue(new Callback<ShowPostResponse>() {
            @Override
            public void onResponse(Call<ShowPostResponse> call, Response<ShowPostResponse> response) {
                ShowPostResponse result = response.body();
                //Toast.makeText(context, result.getresult().get(0).toString(), Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                JsonObject jsonObject = gson.toJsonTree(result.getresult().get(0)).getAsJsonObject();
                //int postid = (int) Float.parseFloat(jsonObject.get("postid").toString());
                String userid = jsonObject.get("userid").toString().replace("\"","");
                String title = jsonObject.get("title").toString().replace("\"","");
                String datetime = jsonObject.get("datetime").toString().replace("\"","");
                String content = jsonObject.get("content").toString().replace("\"","");
                boolean anonymous = ((int) (Float.parseFloat(jsonObject.get("anonymous").toString())) == 1);

                titleview.setText(title);
                nameview.setText(userid);
                dateview.setText(datetime);
                contentview.setText(content);

                //finish();
            }

            @Override
            public void onFailure(Call<ShowPostResponse> call, Throwable t) {
                Toast.makeText(context, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());

            }
        });
    }

    public void GetComment(ShowCommentData data){
        service.getComment(data).enqueue(new Callback<ShowCommentResponse>() {
            @Override
            public void onResponse(Call<ShowCommentResponse> call, Response<ShowCommentResponse> response) {
                ShowCommentResponse result = response.body();
                Gson gson = new Gson();
                for (int i = 0; i < result.getresult().size(); i++) {
                    JsonObject jsonObject = gson.toJsonTree(result.getresult().get(i)).getAsJsonObject();
                    int postid = (int) Float.parseFloat(jsonObject.get("postid").toString());
                    String userid = jsonObject.get("writer").toString().replace("\"","");
                    String content = jsonObject.get("content").toString().replace("\"","");
                    String datetime = jsonObject.get("commentdate").toString().replace("\"","");
                    boolean anonymous = ((int) (Float.parseFloat(jsonObject.get("anonymous").toString())) == 1);
                    cItemList.add(new CommentData(postid, userid, content, datetime));
                }

                commentAdapter.notifyDataSetChanged();

                //finish();
            }

            @Override
            public void onFailure(Call<ShowCommentResponse> call, Throwable t) {
                Toast.makeText(context, "댓글 가져오기 오류", Toast.LENGTH_SHORT).show();
                Log.e("댓글 가져오기 오류", t.getMessage());

            }
        });
    }

    public void WriteComment(WriteCommentData data) {
        service.writeComment(data).enqueue(new Callback<WriteCommentResponse>() {
            @Override
            public void onResponse(Call<WriteCommentResponse> call, Response<WriteCommentResponse> response) {
                WriteCommentResponse result = response.body();
                //Toast.makeText(context, result.getresult().get(0).toString(), Toast.LENGTH_SHORT).show();

                //cItemList.add(new Data(postid,userid,title,datetime));

                //finish();
            }

            @Override
            public void onFailure(Call<WriteCommentResponse> call, Throwable t) {
                Toast.makeText(context, "댓글 작성 오류", Toast.LENGTH_SHORT).show();
                Log.e("댓글 작성 오류", t.getMessage());

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        //if() if id is same as writer
        menuInflater.inflate(R.menu.postactionbar_self, menu);
        //menuInflater.inflate(R.menu.postactionbar_other, menu);
        return true;
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수

    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);


        switch (item.getItemId()) {
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                        finish();
                        return true;
            }
            case R.id.action_search:{

            }
            case R.id.onlypostbyme:{

            }
            case R.id.writepost:{
                Intent intent = new Intent(context, WritePostActivity.class);
                startActivityForResult(intent, 2);
            }
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);

        }


    }


}
