package com.example.retrofitexample.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofitexample.database.AppDatabase;
import com.example.retrofitexample.database.relationship.PlayListWithTracks;

public class SecondFragmentViewModel extends AndroidViewModel {

    private AppDatabase database;
    private MutableLiveData<PlayListWithTracks> playListWithTracksMutableLiveData;

    public SecondFragmentViewModel(@NonNull Application application) {
        super(application);
        database = AppDatabase.getInstance(application);
        playListWithTracksMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<PlayListWithTracks> getPlayListWithTracksMutableLiveData() {
        return playListWithTracksMutableLiveData;
    }

    public void loadDataFromDataBase(final long id){
        new Thread(new Runnable() {
            @Override
            public void run() {
                PlayListWithTracks playListWithTracks = database.playListDao().getPlayListWithTracks(id);

                playListWithTracksMutableLiveData.postValue(playListWithTracks);
            }
        }).start();
    }
}
