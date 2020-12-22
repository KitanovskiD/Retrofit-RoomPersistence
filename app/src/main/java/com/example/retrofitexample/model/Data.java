package com.example.retrofitexample.model;

import android.media.MediaDrm;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Data {
    @PrimaryKey
    @SerializedName("id")
    private long id;
    @SerializedName("title")
    private String title;
    @SerializedName("duration")
    private String duration;

    private long playListId;

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

    public long getPlayListId() {
        return playListId;
    }

    public void setPlayListId(long playListId) {
        this.playListId = playListId;
    }
}
