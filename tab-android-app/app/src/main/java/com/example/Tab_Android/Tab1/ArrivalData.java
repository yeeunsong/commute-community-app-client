package com.example.Tab_Android.Tab1;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class ArrivalData {

    String username;
    boolean arrival;
    String arrival_time;
    String commute_date;

    public ArrivalData(String username, boolean arrival,
                       String arrival_time, String commute_date) {
        this.username = username;
        this.arrival = arrival;
        this.arrival_time = arrival_time;
        this.commute_date = commute_date;
    }
}

