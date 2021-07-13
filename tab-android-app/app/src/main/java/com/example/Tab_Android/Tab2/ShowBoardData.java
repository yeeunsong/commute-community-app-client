package com.example.Tab_Android.Tab2;

import com.google.gson.annotations.SerializedName;

public class ShowBoardData {

    @SerializedName("number")
    int number;

    @SerializedName("nbname")
    String nbname;

    public ShowBoardData(int number, String nbname) {
        this.number = number;
        this.nbname = nbname;
    }
}
