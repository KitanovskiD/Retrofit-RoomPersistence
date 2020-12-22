package com.example.retrofitexample.database.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.retrofitexample.model.Data;
import com.example.retrofitexample.model.PlayList;

import java.util.List;

public class PlayListWithTracks {
    @Embedded
    public PlayList playList;
    @Relation(
            parentColumn = "id",
            entityColumn = "playListId"
    )
    public List<Data> tracks; //Data - Represent One Track

}
