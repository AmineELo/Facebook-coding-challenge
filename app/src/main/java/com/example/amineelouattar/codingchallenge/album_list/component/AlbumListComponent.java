package com.example.amineelouattar.codingchallenge.album_list.component;

import com.example.amineelouattar.codingchallenge.album_list.AlbumListActivity;
import com.example.amineelouattar.codingchallenge.album_list.module.AlbumListModule;
import com.example.amineelouattar.codingchallenge.album_list.module.ContextModule;
import com.example.amineelouattar.codingchallenge.app.component.AppComponent;
import com.example.amineelouattar.codingchallenge.utils.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = {AlbumListModule.class, ContextModule.class})
public interface AlbumListComponent {
    void inject(AlbumListActivity albumListActivity);
}
