package com.example.amineelouattar.codingchallenge.album_list.module;

import com.example.amineelouattar.codingchallenge.album_list.AlbumListContract;
import com.example.amineelouattar.codingchallenge.album_list.AlbumListModel;
import com.example.amineelouattar.codingchallenge.utils.ActivityScope;

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
    public AlbumListContract.AlbumListModel provideModel(){
        return new AlbumListModel();
    }
}
