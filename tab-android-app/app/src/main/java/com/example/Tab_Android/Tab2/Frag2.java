package com.example.Tab_Android.Tab2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Tab_Android.R;
import com.example.Tab_Android.RetrofitClient;
import com.example.Tab_Android.loginAndSignUP.LogSignServiceApi;
import com.example.Tab_Android.loginAndSignUP.LogoutResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        service = RetrofitClient.getClient().create(LogSignServiceApi.class);
        postservice = RetrofitClient.getClient().create(ServiceApi.class);

        mAdapter.setOnItemClickListener(new Frag2Adapter.OnItemClickListener() {
            @Override
            public void onClick(String name) {
                Intent intent = new Intent(getActivity(), ShowBoardActivity.class)
                        .putExtra("nbname",name);
                startActivityForResult(intent,0);
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
        mSearchData.add("우리회사 채용해요");
        mSearchData.add("회사근처 맛집");
        mSearchData.add("이직/커리어");
        mSearchData.add("게임");
        mSearchData.add("스타트업 라운지");
        mSearchData.add("자동차");
        mSearchData.add("여행");

    }
}