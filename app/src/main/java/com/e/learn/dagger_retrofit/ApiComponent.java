package com.e.learn.dagger_retrofit;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class,ApiModule.class})
public interface ApiComponent {

    void inject(MainDaggerRetrofit mainDaggerRetrofit);
}