package com.example.amineelouattar.codingchallenge;

import android.app.Application;

import com.example.amineelouattar.codingchallenge.app.component.AppComponent;
import com.example.amineelouattar.codingchallenge.app.component.DaggerAppComponent;
import com.example.amineelouattar.codingchallenge.app.module.AppModule;

public class BaseApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
