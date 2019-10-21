package com.e.learn.dagger_retrofit;

import android.app.Application;

public class MyApplication extends Application {


    public ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        apiComponent=DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule("https://simplifiedcoding.net/demos/"))
                .build();
    }


    public ApiComponent getApiComponent() {
        return apiComponent;
    }
}
