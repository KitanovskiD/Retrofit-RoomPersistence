package com.example.retrofitexample;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitexample.adapter.TrackAdapter;
import com.example.retrofitexample.api.DeezerApi;
import com.example.retrofitexample.api.DeezerApiClient;
import com.example.retrofitexample.model.Data;
import com.example.retrofitexample.model.PlayList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragment extends Fragment {

    private EditText etPlayListId;
    private DeezerApi deezerApi;
    private TextView tvTitle;
    private ImageView imageView;
    private RecyclerView trackList;

    private TrackAdapter trackAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etPlayListId = (EditText) view.findViewById(R.id.etPlayListId);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        trackList = (RecyclerView) view.findViewById(R.id.trackList);
        trackAdapter = new TrackAdapter(new ArrayList<Data>());

        trackList.setAdapter(trackAdapter);

        deezerApi = DeezerApiClient.getDeezerApiInstance();

        etPlayListId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT){
                    String id = etPlayListId.getText().toString();
                    if (id != null && !id.isEmpty()){
                        searchPlayList(id);
                    }
                    else {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void searchPlayList(String id) {
        deezerApi.getAllPlayList(id).enqueue(new Callback<PlayList>() {
            @Override
            public void onResponse(Call<PlayList> call, Response<PlayList> response) {
                Log.d("RetrofitResponse", "Success");
                displayData(response.body());
            }

            @Override
            public void onFailure(Call<PlayList> call, Throwable t) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //908622995
    //1363560485

    private void displayData(PlayList playList) {
        tvTitle.setText(playList.getTitle());
        Glide.with(this).load(playList.getPicture()).into(imageView);
        trackAdapter.updateData(playList.getTracks().getData());
    }
}