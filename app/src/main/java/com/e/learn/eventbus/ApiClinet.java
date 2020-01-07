package com.e.learn.eventbus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClinet {

    public static final String BASE_URL="https://samples.openweathermap.org/data/2.5/";

    static Retrofit retrofit;

    public static Retrofit getapi(){

        if (retrofit == null) {

            HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .connectTimeout(380, TimeUnit.SECONDS)
                    .readTimeout(380, TimeUnit.SECONDS)
                    .writeTimeout(380, TimeUnit.SECONDS)
                    .build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).client(client)
                    .build();
        }
        return retrofit;
    }
}
