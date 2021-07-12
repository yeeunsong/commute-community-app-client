package com.example.Tab_Android.Tab2;

import com.google.gson.annotations.SerializedName;

public class ShowBoardData {

    @SerializedName("number")
    int number;

    @SerializedName("tablekind")
    String tablekind;

    public ShowBoardData(int number, String tablekind) {
        this.number = number;
        this.tablekind = tablekind;
    }
}
