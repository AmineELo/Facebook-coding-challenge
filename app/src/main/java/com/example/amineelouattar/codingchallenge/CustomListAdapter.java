package com.example.amineelouattar.codingchallenge;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by amineelouattar on 12/7/17.
 */

public class CustomListAdapter extends ArrayAdapter<String> {

    private Activity context;
    private String[] album_titles, album_covers;

    public CustomListAdapter(Activity context, String[] album_titles, String[] album_covers){
        super(context, R.layout.album_row, album_titles);

        this.context = context;
        this.album_titles = album_titles;
        this.album_covers = album_covers;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowView = layoutInflater.inflate(R.layout.album_row, null, true);

        TextView title = (TextView) rowView.findViewById(R.id.album_title);
        ImageView cover = (ImageView) rowView.findViewById(R.id.album_cover);

        title.setText(album_titles[position]);

        Picasso.get().load(album_covers[position])
                .into(cover);


        return rowView;
    }
}
