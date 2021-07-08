package com.example.Tab_Android.Tab0;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.Tab_Android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Frag0 extends Fragment {

    TextView dateView;

    TextView cityView;
    TextView weatherView;
    TextView tempView;

    static RequestQueue requestQueue;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.tab0_activity_frag0,container,false);
        ImageButton button = v.findViewById(R.id.imageButton);
        dateView = v.findViewById(R.id.dateView);
        cityView = v.findViewById(R.id.cityView);
        weatherView = v.findViewById(R.id.weatherView);
        tempView = v.findViewById(R.id.tempView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CurrentCall();
            }
        });

        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        }
        return v;
    }
    private void CurrentCall(){
        String url = "http://api.openweathermap.org/data/2.5/weather?q=daejeon&appid=99162b3225b63083cfc32b51154370bf";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                try {

                    long now = System.currentTimeMillis();
                    Date date = new Date(now);
                    SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("HH:mm:ss");
                    String getDay = simpleDateFormatDay.format(date);
                    String getTime = simpleDateFormatTime.format(date);
                    String getDate = getDay + "\n" + getTime;
                    dateView.setText(getDate);

                    JSONObject jsonObject = new JSONObject(response);

                    String city = jsonObject.getString("name");
                    cityView.setText(city);

                    JSONArray weatherJson = jsonObject.getJSONArray("weather");
                    JSONObject weatherObj = weatherJson.getJSONObject(0);
                    String weather = weatherObj.getString("description");
                    weatherView.setText(weather);

                    JSONObject tempK = new JSONObject(jsonObject.getString("main"));
                    double tempDo = (Math.round((tempK.getDouble("temp")-273.15)*100)/100.0);
                    tempView.setText(tempDo +  "°C");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

        };

        request.setShouldCache(false);
        requestQueue.add(request);
    }
}