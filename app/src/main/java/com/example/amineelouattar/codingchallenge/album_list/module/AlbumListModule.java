package com.example.amineelouattar.codingchallenge.album_list.module;

import com.example.amineelouattar.codingchallenge.album_list.AlbumListContract;
import com.example.amineelouattar.codingchallenge.utils.FacebookDataModel;
import com.example.amineelouattar.codingchallenge.utils.ActivityScope;
import com.example.amineelouattar.codingchallenge.utils.FacebookDataModelInterface;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumListModule {
    private AlbumListContract.AlbumListView view;

    public AlbumListModule(AlbumListContract.AlbumListView view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public AlbumListContract.AlbumListView provideContext(){
        return view;
    }

    @ActivityScope
    @Provides
    public FacebookDataModelInterface provideModel(){
        return new FacebookDataModel();
    }
}
