package com.example.Tab_Android.Tab2;

public class BoardData {
    private int postid;
    private String name,title,date;

    public String getName() {
        return name;
    }



    public BoardData(int postid, String name, String title, String date) {
        this.postid = postid;
        this.name = name;
        this.title = title;
        this.date = date;
    }


    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void getName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
