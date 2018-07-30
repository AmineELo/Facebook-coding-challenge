package com.example.amineelouattar.codingchallenge.pictures_grid;

import android.content.Context;
import android.util.Log;

import com.example.amineelouattar.codingchallenge.utils.facebook_data.FacebookDataModelInterface;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import javax.inject.Inject;

public class GridPicturesPresenter implements GridPictureContract.GridPicturePresenter {

    private GridPictureContract.GridPictureView view;
    private FacebookDataModelInterface model;
    @Inject Context context;

    @Inject
    public GridPicturesPresenter(GridPictureContract.GridPictureView view, FacebookDataModelInterface model) {
        this.view = view;
        this.model = model;
    }


    @Override
    public void getPictures() {
        Log.v("albumId", ((GridPicturesActivity)context).getIntent().getStringExtra("id"));
        String graphPath = "/me?fields=about,name,picture";

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

    }
}
