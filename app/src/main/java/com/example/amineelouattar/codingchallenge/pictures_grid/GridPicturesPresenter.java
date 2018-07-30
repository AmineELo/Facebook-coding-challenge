package com.example.amineelouattar.codingchallenge.pictures_grid;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.amineelouattar.codingchallenge.pictures_grid.adapter.ImageGridAdapter;
import com.example.amineelouattar.codingchallenge.pictures_grid.model.Picture;
import com.example.amineelouattar.codingchallenge.utils.facebook_data.FacebookDataModelInterface;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GridPicturesPresenter implements GridPictureContract.GridPicturePresenter {

    private GridPictureContract.GridPictureView view;
    private FacebookDataModelInterface model;
    @Inject
    Context context;

    @Inject
    public GridPicturesPresenter(GridPictureContract.GridPictureView view, FacebookDataModelInterface model) {
        this.view = view;
        this.model = model;
    }


    @Override
    public void getPictures() {
        String albumId = ((GridPicturesActivity) context).getIntent().getStringExtra("id");
        String graphPath = "/" + albumId + "/photos?fields=picture";

        GraphRequest.Callback responseCallback = new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                extractPictures(response);
            }
        };

        model.executeGraphRequest(AccessToken.getCurrentAccessToken(), graphPath, responseCallback);
    }

    @Override
    public void extractPictures(GraphResponse response) {
        List<Picture> pictures = new ArrayList<>();
        try {
            JSONObject dataResponse = response.getJSONObject();
            JSONArray data = dataResponse.getJSONArray("data");


            for (int i = 0; i < data.length(); i++) {
                pictures.add(new Picture(
                        data.getJSONObject(i).getString("id"),
                        data.getJSONObject(i).getString("picture")
                ));

            }
            view.updatePictureGrid(pictures);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
