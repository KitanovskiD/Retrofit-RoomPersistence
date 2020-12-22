package com.example.retrofitexample.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofitexample.api.DeezerApi;
import com.example.retrofitexample.api.DeezerApiClient;
import com.example.retrofitexample.database.AppDatabase;
import com.example.retrofitexample.model.PlayList;
import com.example.retrofitexample.worker.InsertPlayListWorker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragmentViewModel extends AndroidViewModel {
    private DeezerApi deezerApi;
    private Application app;
    private AppDatabase database;
    private InsertPlayListWorker insertPlayListWorker;

    private MutableLiveData<PlayList> playListMutableLiveData;

    public FirstFragmentViewModel(@NonNull Application application) {
        super(application);
        this.app = application;
        deezerApi = DeezerApiClient.getDeezerApiInstance();
        playListMutableLiveData = new MutableLiveData<>();
        database = AppDatabase.getInstance(application);
        insertPlayListWorker = new InsertPlayListWorker(database);
    }

    public void searchPlayList(String id) {
        deezerApi.getAllPlayList(id).enqueue(new Callback<PlayList>() {
            @Override
            public void onResponse(Call<PlayList> call, Response<PlayList> response) {
                Log.d("RetrofitResponse", "Success");
//                displayData(response.body());
               if(response.body() != null){
                   PlayList playList = response.body();
                   
                   saveInLocalDatabase(playList);

                   playListMutableLiveData.setValue(playList);
               }
               else{
                   Toast.makeText(app, "Error", Toast.LENGTH_SHORT).show();
               }
            }

            @Override
            public void onFailure(Call<PlayList> call, Throwable t) {
                Toast.makeText(app, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveInLocalDatabase(PlayList playList) {
        if(playList.getTracks() != null){
            insertPlayListWorker.execute(playList);
        }
    }

    public MutableLiveData<PlayList> getPlayListMutableLiveData() {
        return playListMutableLiveData;
    }
}
