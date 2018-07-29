package com.example.amineelouattar.codingchallenge.album_list.module;

import android.content.Context;

import com.example.amineelouattar.codingchallenge.utils.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumListModule {
    private Context context;

    public AlbumListModule(Context context) {
        this.context = context;
    }

    @ActivityScope
    @Provides
    public Context provideContext(){
        return context;
    }
}
