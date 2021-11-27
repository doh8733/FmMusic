package com.example.fmmusic.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fmmusic.Model.Songs.Top;
import com.example.fmmusic.R;

import java.util.List;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.TopHolder> {
    private List<Top> topList;

    public TopAdapter(List<Top> topList) {
        this.topList = topList;
    }

    @NonNull
    @Override
    public TopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_top,parent,false);
        return new TopHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopHolder holder, int position) {
        final Top top = topList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(top.getThumbnail())
                .centerCrop()
                .into(holder.imgThumbTopRow);
        holder.tvNameTopRow.setText(top.getName());
        holder.tvPositionTopRow.setText(top.getPosition()+"");
        holder.tvSingerTopRow.setText(top.getSinger().getName());
        if (top.getRankStatus().equalsIgnoreCase("stand")){
            holder.tvStatusTopRow.setBackgroundColor(Color.GRAY);
            holder.tvStatusTopRow.setTextColor(Color.GRAY);
        }
        if (top.getRankStatus().equalsIgnoreCase("up")){
            holder.tvStatusTopRow.setBackgroundColor(Color.GREEN);
            holder.tvStatusTopRow.setTextColor(Color.GREEN);
        }
        if (top.getRankStatus().equalsIgnoreCase("down")){
            holder.tvStatusTopRow.setBackgroundColor(Color.RED);
            holder.tvStatusTopRow.setTextColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return topList.size();
    }

    public class TopHolder extends RecyclerView.ViewHolder {
        private ImageView imgThumbTopRow;
        private TextView tvNameTopRow;
        private TextView tvSingerTopRow;
        private TextView tvPositionTopRow;
        private TextView tvStatusTopRow;

        public TopHolder(@NonNull View itemView) {
            super(itemView);

            imgThumbTopRow = (ImageView) itemView.findViewById(R.id.imgThumbTopRow);
            tvNameTopRow = (TextView) itemView.findViewById(R.id.tvNameTopRow);
            tvSingerTopRow = (TextView) itemView.findViewById(R.id.tvSingerTopRow);
            tvPositionTopRow = (TextView) itemView.findViewById(R.id.tvPositionTopRow);
            tvStatusTopRow = (TextView) itemView.findViewById(R.id.tvStatusTopRow);

        }
    }
}