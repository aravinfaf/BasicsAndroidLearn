package com.e.learn.eventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.e.learn.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventBusActivity extends AppCompatActivity {

    TextView weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        weather = findViewById(R.id.weather);


        Apiservice apiservice=ApiClinet.getapi().create(Apiservice.class);
        Call<WeatherModel> call=apiservice.getweatherapiresponse();

        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful()) {
                    //Toast.makeText(EventService.this, response.body().getCoord().getLat()+"", Toast.LENGTH_SHORT).show();
                    weather.setText(response.body().getCoord().getLat()+"");
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                Toast.makeText(EventBusActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = new Intent(EventBusActivity.this, EventService.class);
        startService(intent);

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(WeatherModel response){
        Toast.makeText(this, "SUBSCRIBE"+response.getCoord().getLat(), Toast.LENGTH_SHORT).show();
        weather.setText(response.getCoord().getLon()+"");

//        if (response.isSuccessful()) {
//            weather.setText(" "+response.body().getCoord().getLat());
//
//            Toast.makeText(EventBusActivity.this, "EventBus "+response.body().getCoord().getLat(), Toast.LENGTH_SHORT).show();
//            Log.e("MESSAGE","EVEnt");
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(EventBusActivity.this);
        Log.e("SSSS", "SSSS");
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(EventBusActivity.this);
        Log.e("SSSS!!!!!", "SSSS!!!!");
    }
}
