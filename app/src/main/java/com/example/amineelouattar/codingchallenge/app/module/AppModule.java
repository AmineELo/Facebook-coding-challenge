package com.example.amineelouattar.codingchallenge.app.module;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.amineelouattar.codingchallenge.utils.Variables;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public Application provideApplication(){
        return application;
    }

    @Singleton
    @Provides
    public SharedPreferences provideSharedPreferences(){
        return application.getSharedPreferences(Variables.SHARED_PREFERENCES, Variables.SHPREF_MODE);
    }
}
