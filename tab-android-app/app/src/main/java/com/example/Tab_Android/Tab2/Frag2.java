package com.example.Tab_Android.Tab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Tab_Android.R;
import com.example.Tab_Android.RetrofitClient;
import com.example.Tab_Android.loginAndSignUP.LogSignServiceApi;

import java.util.ArrayList;

//외부에서 new Frag2 호출 시
public class Frag2 extends Fragment {

    private static final String TAG = "Frag2";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList mSearchData;
    private Frag2Adapter mAdapter;
    private LogSignServiceApi service;
    private ServiceApi postservice;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.tab2_community_main,container,false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.tab2RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);
        mAdapter = new Frag2Adapter(mSearchData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        textView = v.findViewById(R.id.test);
        service = RetrofitClient.getClient().create(LogSignServiceApi.class);
        postservice = RetrofitClient.getClient().create(ServiceApi.class);

        Button button1 = (Button) v.findViewById(R.id.login);
        Button button2 = (Button) v.findViewById(R.id.register);
        Button button3 = (Button) v.findViewById(R.id.logout);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ServerThread thread = new ServerThread();
                //thread.start();
                Intent intent = new Intent(getActivity(), com.example.Tab_Android.loginAndSignUP.LoginActivity.class);
                startActivityForResult(intent,0);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ServerThread thread = new ServerThread();
                //thread.start();
                Intent intent = new Intent(getActivity(), com.example.Tab_Android.loginAndSignUP.JoinActivity.class);
                startActivityForResult(intent,1);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                //logout
                service.userLogout().enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        //LoginResponse result = response.body();
                        Toast.makeText(getContext(), "로그아웃 성공!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(getContext(), "로그아웃 에러 발생", Toast.LENGTH_SHORT).show();
                        Log.e("로그아웃 에러 발생", t.getMessage());
                    }
                });*/

            }
        });

        mAdapter.setOnItemClickListener(new Frag2Adapter.OnItemClickListener() {
            @Override
            public void onClick(String name) {
                Intent intent = new Intent(getActivity(), ShowBoardActivity.class)
                        .putExtra("nbname",name);
                startActivityForResult(intent,0);
                //Toast.makeText(getActivity(), "되냐?", Toast.LENGTH_LONG).show();
            }
        });

        mAdapter.notifyDataSetChanged();
        return v;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    private void initDataset() {
        //for Test
        mSearchData = new ArrayList<>();
        mSearchData.add("무슨무슨 게시판");
        mSearchData.add("이런 저런 게시판");
        mSearchData.add("아무튼 게시판임");

    }
}