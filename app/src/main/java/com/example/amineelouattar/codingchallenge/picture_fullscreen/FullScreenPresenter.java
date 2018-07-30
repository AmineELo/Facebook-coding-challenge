package com.example.amineelouattar.codingchallenge.picture_fullscreen;

import android.content.Context;

import com.example.amineelouattar.codingchallenge.utils.facebook_data.FacebookDataModelInterface;
import com.facebook.GraphResponse;

import javax.inject.Inject;

public class FullScreenPresenter implements FullScreenContract.FullScreenPresenter {

    private FullScreenContract.FullScreenView view;
    private FacebookDataModelInterface model;
    @Inject
    Context context;

    @Inject
    public FullScreenPresenter(FullScreenContract.FullScreenView view, FacebookDataModelInterface model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void getPicture() {

    }

    @Override
    public void extractPicture(GraphResponse response) {

    }
}
