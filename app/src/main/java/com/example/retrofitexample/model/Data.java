package com.example.retrofitexample.model;

import android.media.MediaDrm;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("id")
    private long id;
    @SerializedName("title")
    private String title;
    @SerializedName("duration")
    private String duration;

    public Data() {
    }

    public Data(long id, String title, String duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
