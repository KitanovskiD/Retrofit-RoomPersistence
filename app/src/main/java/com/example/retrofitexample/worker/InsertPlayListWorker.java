package com.example.retrofitexample.worker;

import android.os.AsyncTask;

import com.example.retrofitexample.database.AppDatabase;
import com.example.retrofitexample.model.PlayList;

public class InsertPlayListWorker extends AsyncTask<PlayList, Void, Void> {

    private AppDatabase database;

    public InsertPlayListWorker(AppDatabase database){
        this.database = database;
    }

    @Override
    protected Void doInBackground(PlayList... playLists) {
        database.playListDao().insertPlayListWithTracks(playLists[0], playLists[0].getTracks().getData());
        return null;
    }
}
