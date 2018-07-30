package com.example.amineelouattar.codingchallenge.pictures_grid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.amineelouattar.codingchallenge.R;
import com.example.amineelouattar.codingchallenge.pictures_grid.model.Picture;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by amineelouattar on 12/7/17.
 */

public class ImageGridAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<Picture> pictureList;

    public ImageGridAdapter(Context context, List<Picture> pictureList) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.pictureList = pictureList;

    }

    @Override
    public int getCount() {
        return pictureList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(pictureList.get(position).getId());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        View v = view;
        if (v == null) {
            v = inflater.inflate(R.layout.item_grid_image, viewGroup, false);
            holder = new ViewHolder();
            assert v != null;

            holder.imageView = v.findViewById(R.id.image);

            holder.progressBar = v.findViewById(R.id.progress);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        Picasso.get()
                .load(pictureList.get(i).getPictureUrl())
                .placeholder(R.drawable.placeholderthumbnail)
                .error(R.drawable.androiderror)
                .fit()
                .into(holder.imageView);

        return v;
    }

    public void updatePictures(List<Picture> pictureList){
        this.pictureList = pictureList;
        notifyDataSetChanged();
    }

    class ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
    }

}
