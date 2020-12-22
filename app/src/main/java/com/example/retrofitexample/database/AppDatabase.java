package com.example.retrofitexample.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.retrofitexample.database.dao.PlayListDao;
import com.example.retrofitexample.model.Data;
import com.example.retrofitexample.model.PlayList;

@Database(entities =  {PlayList.class, Data.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public  abstract PlayListDao playListDao();

    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null) {
            instance = create(context);
        }

        return instance;
    }

    private static AppDatabase create(final Context context){
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                "playlist.db").build();
    }
}
