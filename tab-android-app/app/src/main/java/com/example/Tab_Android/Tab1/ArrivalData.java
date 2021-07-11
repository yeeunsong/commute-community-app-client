package com.example.Tab_Android.Tab1;

import com.google.gson.annotations.SerializedName;

public class ArrivalData {
    // DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    // Dates format like String "20210909" is normally received by the MySQL database.
    @SerializedName("username")
    String username;
    @SerializedName("arrival")
    boolean arrival;
    @SerializedName("arrival_time")
    String arrival_time;
    @SerializedName("commute_date")
    String commute_date;

    public ArrivalData(String username, boolean arrival, String arrival_time, String commute_date) {
        this.username = username;
        this.arrival = arrival;
        this.arrival_time = arrival_time;
        this.commute_date = commute_date;
    }

    public String getUsername() {
        return username;
    }

    public boolean isArrival() {
        return arrival;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public String getCommute_date() {
        return commute_date;
    }
}
