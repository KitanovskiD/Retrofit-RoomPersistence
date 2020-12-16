package com.example.retrofitexample.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Track {
    @SerializedName("data")
    private List<Data> data;

    public Track() {
    }

    public Track(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
