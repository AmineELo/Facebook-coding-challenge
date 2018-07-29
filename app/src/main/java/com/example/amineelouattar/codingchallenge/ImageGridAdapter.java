package com.example.amineelouattar.codingchallenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

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
        Picasso.with(context)
                .load(images[i])
                .placeholder(R.drawable.placeholderthumbnail)
                .error(R.drawable.androiderror)
                .fit()
                .into(holder.imageView, new Callback() {

                    @Override
                    public void onSuccess() {
                        holder.imageView.setVisibility(View.VISIBLE);
                        holder.progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {
                        holder.progressBar.setVisibility(View.VISIBLE);
                        holder.imageView.setVisibility(View.INVISIBLE);
                    }
                });

        return v;
    }

    class ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
    }

}
