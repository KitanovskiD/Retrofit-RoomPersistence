package com.example.retrofitexample.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeezerApiClient {
    private static DeezerApi deezerClient = null;

    public static DeezerApi getDeezerApiInstance(){
        if(deezerClient == null){
            deezerClient = new Retrofit.Builder()
                    .baseUrl("http://api.deezer.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(DeezerApi.class);
        }

        return deezerClient;
    }
}
