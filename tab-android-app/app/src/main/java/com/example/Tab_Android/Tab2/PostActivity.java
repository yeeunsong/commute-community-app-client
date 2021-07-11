package com.example.Tab_Android.Tab2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Tab_Android.R;
import com.example.Tab_Android.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {
    Context context;
    TextView titleview, nameview, dateview, contentview;


    private ServiceApi service;
    private RecyclerView commentRecyclerView; // 리스트뷰
    private ArrayList<CommentData> cItemList = new ArrayList<>();
    PostAdapter commentAdapter;
    RecyclerView.LayoutManager layoutManager;
    JsonParser jsonParser = new JsonParser();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2_postboard);
        context = this.getBaseContext();
        service = RetrofitClient.getClient().create(ServiceApi.class);

        titleview = (TextView)findViewById(R.id.posttitle);
        nameview = (TextView)findViewById(R.id.postname);
        dateview = (TextView)findViewById(R.id.postdate);
        contentview = (TextView)findViewById(R.id.postcontent);

        commentRecyclerView = (RecyclerView) findViewById(R.id.commentrecyclerView);
        commentRecyclerView.setHasFixedSize(true);
        // Set Layout Manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        commentRecyclerView.setLayoutManager(layoutManager);

        commentAdapter = new PostAdapter(cItemList);

        commentRecyclerView.setAdapter(commentAdapter); // 어댑터를 리스트뷰에 세팅

        init();
    }

    private void init(){
        //System.out.println(getIntent().getExtras().getInt("postid"));
        GetPostData(new ShowPostData(getIntent().getExtras().getInt("postid")));
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
                String userid = jsonObject.get("userid").toString();
                String title = jsonObject.get("title").toString();
                String datetime = jsonObject.get("datetime").toString();
                String content = jsonObject.get("content").toString();

                titleview.setText(title);
                nameview.setText(userid);
                dateview.setText(datetime);
                contentview.setText(content);

                boolean anonymous = ((int) (Float.parseFloat(jsonObject.get("anonymous").toString())) == 1);
                //cItemList.add(new Data(postid,userid,title,datetime));

                //finish();
            }

            @Override
            public void onFailure(Call<ShowPostResponse> call, Throwable t) {
                Toast.makeText(context, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());

            }
        });
    }


}
