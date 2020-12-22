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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitexample.adapter.TrackAdapter;
import com.example.retrofitexample.api.DeezerApi;
import com.example.retrofitexample.api.DeezerApiClient;
import com.example.retrofitexample.model.Data;
import com.example.retrofitexample.model.PlayList;
import com.example.retrofitexample.viewmodel.FirstFragmentViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragment extends Fragment {

    private EditText etPlayListId;
    private TextView tvTitle;
    private ImageView imageView;
    private RecyclerView trackList;

    private TrackAdapter trackAdapter;

    private FirstFragmentViewModel firstFragmentViewModel;

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

        firstFragmentViewModel = ViewModelProviders.of(this).get(FirstFragmentViewModel.class);

        firstFragmentViewModel.getPlayListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<PlayList>() {
            @Override
            public void onChanged(PlayList playList) {
                displayData(playList);
            }
        });

        view.findViewById(R.id.openSecondFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        etPlayListId = (EditText) view.findViewById(R.id.etPlayListId);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        trackList = (RecyclerView) view.findViewById(R.id.trackList);
        trackAdapter = new TrackAdapter(new ArrayList<Data>());

        trackList.setAdapter(trackAdapter);

        etPlayListId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT){
                    String id = etPlayListId.getText().toString();
                    if (id != null && !id.isEmpty()){
                        firstFragmentViewModel.searchPlayList(id);
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

    //908622995
    //1363560485

    private void displayData(PlayList playList) {
        tvTitle.setText(playList.getTitle());
        Glide.with(this).load(playList.getPicture()).into(imageView);
        trackAdapter.updateData(playList.getTracks().getData());
    }
}