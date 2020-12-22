package com.example.retrofitexample.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class PlayList {
    @PrimaryKey
    @SerializedName("id")
    private long id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("picture")
    private String picture;

    @Ignore
    @SerializedName("tracks")
    private Track tracks;

    public PlayList() {
    }

    public PlayList(long id, String title, String description, String picture, Track tracks) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.tracks = tracks;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Track getTracks() {
        return tracks;
    }

    public void setTracks(Track tracks) {
        this.tracks = tracks;
    }
}
