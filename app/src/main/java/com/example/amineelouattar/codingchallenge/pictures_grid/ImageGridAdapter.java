package com.example.amineelouattar.codingchallenge.pictures_grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.amineelouattar.codingchallenge.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by amineelouattar on 12/7/17.
 */

public class ImageGridAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private String[] images;

    public ImageGridAdapter(Context context, String[] images) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.images = images;

    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        View v = view;
        if (v == null) {
            v = inflater.inflate(R.layout.item_grid_image, viewGroup, false);
            holder = new ViewHolder();
            assert v != null;

            holder.imageView = (ImageView) v.findViewById(R.id.image);

            holder.progressBar = (ProgressBar) v.findViewById(R.id.progress);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        Picasso.get()
                .load(images[i])
                .placeholder(R.drawable.placeholderthumbnail)
                .error(R.drawable.androiderror)
                .fit()
                .into(holder.imageView);

        return v;
    }

    class ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
    }

}
