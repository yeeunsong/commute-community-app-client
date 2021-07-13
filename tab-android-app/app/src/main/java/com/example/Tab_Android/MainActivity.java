package com.example.Tab_Android;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


import com.example.Tab_Android.Tab2.Frag2;
import com.example.Tab_Android.Tab1.Frag1;

import com.example.Tab_Android.Tab2.WritePostActivity;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this.getBaseContext();
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.maintoolbar);

        setSupportActionBar(toolbar);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainactionbar, menu);

        return true;
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수

    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.logout:{


                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("정말 로그아웃하시겠습니까?");

                builder.setPositiveButton("네", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Toast.makeText(getApplicationContext(), "로그아웃되었습니다!", Toast.LENGTH_LONG).show();
                        setResult(RESULT_OK);
                        finish();
                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        //Toast.makeText(getApplicationContext(), "Cancel Click", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                positiveButton.setTextColor(Color.parseColor("#FF000000"));
                negativeButton.setTextColor(Color.parseColor("#FF000000"));
                break;
            }

            case R.id.myinfo:{
                Intent intent = new Intent(context, InfoActivity.class);
                //Toast.makeText(getApplicationContext(), "info도 클릭됨", Toast.LENGTH_LONG).show();
                startActivityForResult(intent,0);
                break;
            }

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                //Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();

        }
        return super.onOptionsItemSelected(item);
    }
}