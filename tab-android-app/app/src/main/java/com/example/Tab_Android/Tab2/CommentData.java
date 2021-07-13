package com.example.Tab_Android.Tab2;

public class CommentData {
    private int postid;
    private String name,content,date;

    public CommentData(int postid, String name, String content, String date) {
        this.postid = postid;
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
