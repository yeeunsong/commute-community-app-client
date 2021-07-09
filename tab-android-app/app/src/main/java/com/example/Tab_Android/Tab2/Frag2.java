package com.example.Tab_Android.Tab2;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.Tab_Android.loginAndSignUP.JoinActivity;
import com.example.Tab_Android.loginAndSignUP.LoginActivity;

import java.util.ArrayList;

//외부에서 new Frag2 호출 시
public class Frag2 extends Fragment {
    private static final String TAG = "Frag2";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<TableData> mSearchData;
    private MainAdapter mAdapter;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.tab2_community_main,container,false);
        View view = inflater.inflate(R.layout.tab2_community_main, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);
        mAdapter = new MainAdapter(mSearchData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        textView = v.findViewById(R.id.test);

        Button button1 = (Button) v.findViewById(R.id.login);
        Button button2 = (Button) v.findViewById(R.id.register);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ServerThread thread = new ServerThread();
                //thread.start();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent,0);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ServerThread thread = new ServerThread();
                //thread.start();
                Intent intent = new Intent(getActivity(), JoinActivity.class);
                startActivityForResult(intent,1);

            }
        });


        mAdapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onClick(String name) {
                //Intent intent = new Intent(getActivity(), AddPhoneBook.class);
                //startActivityForResult(intent,0);
                Toast.makeText(getActivity(), "되냐?", Toast.LENGTH_LONG).show();
            }
        });





        mAdapter.notifyDataSetChanged();
        return v;



    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }


    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==0){
            //logined?

        }

    }


    private void initDataset() {
        //for Test
        mSearchData = new ArrayList<>();
        mSearchData.add(new TableData("무슨무슨 게시판","",""));
        mSearchData.add(new TableData("이런 저런 게시판","",""));
        mSearchData.add(new TableData("아무튼 게시판임 ㅇㅇ","",""));

    }




}