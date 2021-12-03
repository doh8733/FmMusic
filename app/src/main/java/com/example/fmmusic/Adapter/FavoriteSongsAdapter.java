package com.example.fmmusic.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fmmusic.Model.Favorite;
import com.example.fmmusic.Model.Songs.Top;
import com.example.fmmusic.R;
import com.example.fmmusic.View.Activity.MusicPlayingActivity;

import java.util.List;

public class FavoriteSongsAdapter extends RecyclerView.Adapter<FavoriteSongsAdapter.FavoriteSongHolder> {
    private List<Favorite> favoriteList;

    public FavoriteSongsAdapter(List<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public FavoriteSongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_suggested_song,parent,false);
        return new FavoriteSongHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteSongHolder holder, int position) {
        final Favorite favorite = favoriteList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(favorite.getSong().getThumbnail())
                .centerCrop()
                .into(holder.imgThumbTopRow);

        holder.tvNameTopRow.setText(favorite.getSong().getName());
        holder.tvSingerTopRow.setText(favorite.getSong().getSinger().getName());
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class FavoriteSongHolder extends RecyclerView.ViewHolder {
        private ImageView imgThumbTopRow;
        private TextView tvNameTopRow;
        private TextView tvSingerTopRow;

        public FavoriteSongHolder(@NonNull View itemView) {
            super(itemView);
            imgThumbTopRow = (ImageView) itemView.findViewById(R.id.imgThumbTopRow);
            tvNameTopRow = (TextView) itemView.findViewById(R.id.tvNameTopRow);
            tvSingerTopRow = (TextView) itemView.findViewById(R.id.tvSingerTopRow);
        }
    }
}
