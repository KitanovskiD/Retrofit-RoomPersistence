package com.example.retrofitexample;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitexample.adapter.TrackAdapter;
import com.example.retrofitexample.database.relationship.PlayListWithTracks;
import com.example.retrofitexample.model.Data;
import com.example.retrofitexample.model.PlayList;
import com.example.retrofitexample.viewmodel.FirstFragmentViewModel;
import com.example.retrofitexample.viewmodel.SecondFragmentViewModel;

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    private SecondFragmentViewModel secondFragmentViewModel;

    private RecyclerView trackList;

    private TrackAdapter trackAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        secondFragmentViewModel = ViewModelProviders.of(this).get(SecondFragmentViewModel.class);

        secondFragmentViewModel.getPlayListWithTracksMutableLiveData().observe(getViewLifecycleOwner(), new Observer<PlayListWithTracks>() {
            @Override
            public void onChanged(PlayListWithTracks playListWithTracks) {
                displayData(playListWithTracks);
            }
        });

        secondFragmentViewModel.loadDataFromDataBase(908622995);

        trackList = (RecyclerView) view.findViewById(R.id.secondTrackList);
        trackAdapter = new TrackAdapter(new ArrayList<Data>());

        trackList.setAdapter(trackAdapter);


    }

    private void displayData(PlayListWithTracks playListWithTracks) {
        //tvTitle.setText(playList.getTitle());
        //Glide.with(this).load(playListWithTracks.playList.getPicture()).into(imageView);
        trackAdapter.updateData(playListWithTracks.tracks);
    }
}