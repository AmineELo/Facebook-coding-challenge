package com.example.amineelouattar.codingchallenge.app.component;

import com.example.amineelouattar.codingchallenge.app.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
}
