package com.example.amineelouattar.codingchallenge.picture_fullscreen.component;

import com.example.amineelouattar.codingchallenge.app.component.AppComponent;
import com.example.amineelouattar.codingchallenge.picture_fullscreen.FullScreenActivity;
import com.example.amineelouattar.codingchallenge.picture_fullscreen.module.FullScreenModule;
import com.example.amineelouattar.codingchallenge.utils.ActivityScope;
import com.example.amineelouattar.codingchallenge.utils.module.ContextModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = {FullScreenModule.class, ContextModule.class})
public interface FullScreenComponent {
    void inject(FullScreenActivity fullScreenActivity);
}
