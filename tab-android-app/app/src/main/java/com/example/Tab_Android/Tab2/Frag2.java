package com.example.Tab_Android.Tab2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.Tab_Android.R;

//외부에서 new Frag2 호출 시
public class Frag2 extends Fragment {
    private static final String TAG = "Frag2";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.tab2_community_main,container,false);
        return v;
    }

}