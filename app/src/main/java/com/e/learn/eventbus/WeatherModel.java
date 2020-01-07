package com.e.learn.eventbus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherModel {


    @SerializedName("coord")
    @Expose
    private WeatherResponseModel coord;

    public WeatherResponseModel getCoord() {
        return coord;
    }

    public void setCoord(WeatherResponseModel coord) {
        this.coord = coord;
    }
}
