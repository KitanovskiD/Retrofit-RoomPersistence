package com.example.retrofitexample.api;

import com.example.retrofitexample.model.PlayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DeezerApi {
    @GET("playlist/{id}")
    Call<PlayList> getAllPlayList(@Path("id") String id);
}
