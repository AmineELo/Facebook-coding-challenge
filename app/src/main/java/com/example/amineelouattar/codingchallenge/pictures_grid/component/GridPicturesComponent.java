package com.example.amineelouattar.codingchallenge.pictures_grid.component;

import com.example.amineelouattar.codingchallenge.app.component.AppComponent;
import com.example.amineelouattar.codingchallenge.pictures_grid.GridPicturesActivity;
import com.example.amineelouattar.codingchallenge.pictures_grid.module.GridPicturesModule;
import com.example.amineelouattar.codingchallenge.utils.ActivityScope;
import com.example.amineelouattar.codingchallenge.utils.module.ContextModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = {GridPicturesModule.class, ContextModule.class})
public interface GridPicturesComponent {
    void inject(GridPicturesActivity gridPicturesActivity);
}
