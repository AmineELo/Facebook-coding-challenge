package com.example.amineelouattar.codingchallenge.album_list.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amineelouattar.codingchallenge.R;
import com.example.amineelouattar.codingchallenge.album_list.model.Album;
import com.example.amineelouattar.codingchallenge.pictures_grid.GridPicturesActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.AlbumListViewHolder> {

    private List<Album> albumList;
    private Context context;

    public AlbumListAdapter(List<Album> albumList, Context context) {
        this.albumList = albumList;
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_row, parent, false);

        return new AlbumListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumListViewHolder holder, int position) {
        holder.albumTitle.setText(albumList.get(position).getAlbumName());
        holder.albumCount.setText(context.getString(R.string.photo_count_holder, albumList.get(position).getAlbumCount()));
        Picasso.get()
                .load(albumList.get(position).getAlbumCoverUrl())
                .into(holder.albumCover);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public void updateAlbumList(List<Album> albumList){
        this.albumList = albumList;
        notifyDataSetChanged();
    }

    class AlbumListViewHolder extends RecyclerView.ViewHolder{

        private ImageView albumCover;
        private TextView albumTitle, albumCount;

        private AlbumListViewHolder(View itemView) {
            super(itemView);
            albumCover = itemView.findViewById(R.id.album_cover);
            albumTitle = itemView.findViewById(R.id.album_title);
            albumCount = itemView.findViewById(R.id.album_count);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, GridPicturesActivity.class);
                    intent.putExtra("id", albumList.get(getAdapterPosition()).getAlbumId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
