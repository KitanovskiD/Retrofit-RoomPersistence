package com.example.retrofitexample.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.retrofitexample.database.relationship.PlayListWithTracks;
import com.example.retrofitexample.model.Data;
import com.example.retrofitexample.model.PlayList;

import java.util.List;

@Dao
public abstract class PlayListDao {

    @Transaction
    @Query("SELECT * FROM PlayList WHERE id = :id")
    public abstract PlayListWithTracks getPlayListWithTracks(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertPlayList(PlayList playList);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertTracks(List<Data> data);

    public void insertPlayListWithTracks(PlayList playList, List<Data> tracks){
       try {
           insertPlayList(playList);
           for(Data track : tracks){
               track.setPlayListId(playList.getId());
           }
           insertTracks(tracks);
       }
       catch (Exception e){
           e.printStackTrace();
       }
    }

}
