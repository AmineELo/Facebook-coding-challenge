package com.example.amineelouattar.codingchallenge.picture_fullscreen.module;

import com.example.amineelouattar.codingchallenge.picture_fullscreen.FullScreenContract;
import com.example.amineelouattar.codingchallenge.utils.ActivityScope;
import com.example.amineelouattar.codingchallenge.utils.facebook_data.FacebookDataModel;
import com.example.amineelouattar.codingchallenge.utils.facebook_data.FacebookDataModelInterface;

import dagger.Module;
import dagger.Provides;

@Module
public class FullScreenModule {

    private FullScreenContract.FullScreenView view;

    public FullScreenModule(FullScreenContract.FullScreenView view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public FullScreenContract.FullScreenView provideView() {
        return view;
    }

    @ActivityScope
    @Provides
    public FacebookDataModelInterface provideModel(){
        return new FacebookDataModel();
    }
}
