package com.example.amineelouattar.codingchallenge.pictures_grid.module;

import com.example.amineelouattar.codingchallenge.pictures_grid.GridPictureContract;
import com.example.amineelouattar.codingchallenge.utils.ActivityScope;
import com.example.amineelouattar.codingchallenge.utils.facebook_data.FacebookDataModel;
import com.example.amineelouattar.codingchallenge.utils.facebook_data.FacebookDataModelInterface;

import dagger.Module;
import dagger.Provides;

@Module
public class GridPicturesModule {

    private GridPictureContract.GridPictureView view;

    public GridPicturesModule(GridPictureContract.GridPictureView view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public GridPictureContract.GridPictureView provideView(){
        return view;
    }

    @ActivityScope
    @Provides
    public FacebookDataModelInterface provideModel(){
        return new FacebookDataModel();
    }
}
