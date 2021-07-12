package com.example.Tab_Android.Tab2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Tab_Android.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WritePostActivity extends AppCompatActivity {

    Context context;
    private ServiceApi service;
    private AutoCompleteTextView titleView;
    private EditText contentView;
    private CheckBox anonymouscheck;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2_writepost);
        context = this.getBaseContext();
        titleView = (AutoCompleteTextView) findViewById(R.id.post_title);
        contentView = (EditText) findViewById(R.id.post_content);
        anonymouscheck = (CheckBox) findViewById(R.id.anonymous);



        Button buttonuploadpost = (Button) findViewById(R.id.writepost);

        buttonuploadpost.setOnClickListener(new View.OnClickListener() {
            String title = titleView.getText().toString();
            String postcontent = contentView.getText().toString();
            boolean anonymous = anonymouscheck.isChecked();
            @Override
            public void onClick(View v) {
                startpost(new WritePostData(title,postcontent,anonymous));

            }
        });
    }

    public void startpost(WritePostData data){
        service.writePost(data).enqueue(new Callback<WritePostResponse>() {
            @Override
            public void onResponse(Call<WritePostResponse> call, Response<WritePostResponse> response) {
                WritePostResponse result = response.body();
                Toast.makeText(context, "로그아웃 성공!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(context, "로그아웃 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그아웃 에러 발생", t.getMessage());
            }
        });
    }


}
