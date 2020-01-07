package com.e.learn.eventbus;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventService extends IntentService {

    private static final long DELAY = 5000; // ms

    public EventService() {
        super(EventService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if(intent!=null){
            startwork(intent);
            }
        scheduleNextStart(DELAY);
    }

    private void scheduleNextStart(long delay) {

        ((AlarmManager) getSystemService(Context.ALARM_SERVICE)).set(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + delay,
                getPendingIntent(this));    }

    public static PendingIntent getPendingIntent(@NonNull Context context) {
        Intent intent = new Intent(context, EventService.class);
        return PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void startwork(Intent intent) {

        Apiservice apiservice=ApiClinet.getapi().create(Apiservice.class);
        Call<WeatherModel> call=apiservice.getweatherapiresponse();
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful()) {
                        EventBus.getDefault().post(response.body());
                    //Toast.makeText(EventService.this, response.body().getCoord().getLat()+"", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                Toast.makeText(EventService.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
