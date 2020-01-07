package com.e.learn.eventbus;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface Apiservice {

    @GET("weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22")
    Call<WeatherModel> getweatherapiresponse();

}
