package com.example.retrofitexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitexample.R;
import com.example.retrofitexample.model.Data;

import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {

    private List<Data> trackList;

    public TrackAdapter(List<Data> trackList) {
        this.trackList = trackList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(trackList.get(position));
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    public void updateData(List<Data> data){
        this.trackList = data;
        this.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTrackId, tvTrackTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTrackId = (TextView) itemView.findViewById(R.id.tvTrackId);
            tvTrackTitle = (TextView) itemView.findViewById(R.id.tvTrackTitle);
        }

        public void bindData(Data data){
            tvTrackId.setText(String.valueOf(data.getId()));
            tvTrackTitle.setText(data.getTitle());
        }
    }
}
