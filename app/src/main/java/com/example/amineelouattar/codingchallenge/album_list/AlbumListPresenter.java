package com.example.amineelouattar.codingchallenge.album_list;

import android.content.Context;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import javax.inject.Inject;

public class AlbumListPresenter implements AlbumListContract.AlbumListPresenter {

    private AlbumListContract.AlbumListView view;
    private AlbumListContract.AlbumListModel model;
    @Inject
    Context context;

    @Inject
    public AlbumListPresenter(AlbumListContract.AlbumListView view, AlbumListContract.AlbumListModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void getUserInfo() {
        String graphPath = "/me?fields=about,name,picture";

        GraphRequest.Callback responseCallback = new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                extractAlbums(response);
            }
        };

        model.executeGraphRequest(AccessToken.getCurrentAccessToken(), graphPath, responseCallback);
    }

    @Override
    public void getAlbums() {

    }

    @Override
    public void extractAlbums(GraphResponse response) {

    }

    @Override
    public void extractUserInfo(GraphResponse response) {

    }
}
