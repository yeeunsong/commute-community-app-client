package com.example.Tab_Android.Tab2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.Tab_Android.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class Table extends Fragment {

    private RecyclerView mRecyclerView;
    private TableAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList mSearchData;
    private int count = 0;
    CoordinatorLayout coordinatorLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab2_table, container, false);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.tableRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);
        mAdapter = new TableAdapter(mSearchData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        SwipeRefreshLayout swipeRefreshLayout= view.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        mAdapter.setOnItemClickListener(new TableAdapter.OnItemClickListener() {
            @Override
            public void onClick(String name) {

            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //여기서 새로고침을!!!
                mAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    private void initDataset() {
        //for Test
        mSearchData = new ArrayList<>();
        //mSearchData.add(new TableData("Kimjanghyun","Computer of Science", "010-3646-1933","big01ad@kaist.ac.kr"));
        //mSearchData.add(new TableData("Lee HoJun","Biological Science", "010-1234-5678","dontknow@unist.ac.kr"));
        //mSearchData.add(new TableData("tester","Electrical Engineering", "010-8765-4321","abcd@kaist.ac.kr"));
    }
}