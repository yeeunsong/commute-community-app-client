package com.example.Tab_Android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.Tab_Android.Tab2.Frag2;
import com.example.Tab_Android.Tab1.Frag1;

import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tabs);
        viewPager=findViewById(R.id.view_pager);
        adapter=new FragmentAdapter(getSupportFragmentManager(),1);

        //FragmentAdapter에 컬렉션 담기
        adapter.addFragment(new Frag1());
        adapter.addFragment(new Frag2());


        //ViewPager Fragment 연결
        viewPager.setAdapter(adapter);

        //ViewPager과 TabLayout 연결
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Commute");
        tabLayout.getTabAt(1).setText("Community");

    }
}