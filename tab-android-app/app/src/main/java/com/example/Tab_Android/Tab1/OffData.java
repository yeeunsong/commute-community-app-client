package com.example.Tab_Android.Tab1;

import com.google.gson.annotations.SerializedName;

public class OffData {
    String username;
    boolean off;
    String off_time;
    String total_time;
    String commute_date;

    public OffData(String username, boolean off, String off_time, String total_time, String commute_date) {
        this.username = username;
        this.off = off;
        this.off_time = off_time;
        this.total_time = total_time;
        this.commute_date = commute_date;
    }


}
