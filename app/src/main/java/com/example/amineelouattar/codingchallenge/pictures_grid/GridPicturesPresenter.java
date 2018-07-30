package com.example.amineelouattar.codingchallenge.pictures_grid;

import com.example.amineelouattar.codingchallenge.utils.facebook_data.FacebookDataModelInterface;
import com.facebook.GraphResponse;

import javax.inject.Inject;

public class GridPicturesPresenter implements GridPictureContract.GridPicturePresenter {

    private GridPictureContract.GridPictureView view;
    private FacebookDataModelInterface model;

    @Inject
    public GridPicturesPresenter(GridPictureContract.GridPictureView view, FacebookDataModelInterface model) {
        this.view = view;
        this.model = model;
    }


    @Override
    public void getPictures() {

    }

    @Override
    public void extractPictures(GraphResponse response) {

    }
}
