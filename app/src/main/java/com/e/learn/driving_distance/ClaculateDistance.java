package com.e.learn.driving_distance;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.e.learn.R;
import com.google.android.gms.maps.model.LatLng;

public class ClaculateDistance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claculate_distance);

        CalculateDistanceTime distance_task = new CalculateDistanceTime(ClaculateDistance.this);

        double lat1=11.0802;
        double lat2=11.0007;
        double lon1=76.9415;
        double lon2=77.0296;

        LatLng startLatLng=new LatLng(lat1,lon1);
        LatLng endLatLng=new LatLng(lat2,lon2);


        distance_task.getDirectionsUrl(startLatLng, endLatLng);

        distance_task.setLoadListener(new CalculateDistanceTime.taskCompleteListener() {
            @Override
            public void taskCompleted(String[] time_distance) {
                //approximate_time.setText("" + time_distance[1]);
               // approximate_diatance.setText("" + time_distance[0]);

                Log.e("DIS",time_distance[0]+"");
                Toast.makeText(ClaculateDistance.this, time_distance[0]+"///"+ time_distance[1], Toast.LENGTH_SHORT).show();

            }

        });
    }
}