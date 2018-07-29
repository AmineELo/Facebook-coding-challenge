package com.example.amineelouattar.codingchallenge.album_list;

import android.content.Context;

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
