package com.e.learn.recyclerviewmodel;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.e.learn.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import java.util.List;

public class RecyclerMain extends AppCompatActivity {

    RecyclerView recyclerview;
    HeroesViewModel viewModel;
    HeroesAdapter adapter;
    AudioManager audioManager;
    int  maxVolume ;
    int volume;
    String TAG="FCM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_main);



        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }

// Get the Instance ID token//
                        String token = task.getResult().getToken();
                        String msg = getString(R.string.fcm_token, token);
                        Log.d(TAG, msg);

                    }
                });


        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        final int volume_level = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = (int) volume_level;
        Toast.makeText(this, ""+volume, Toast.LENGTH_SHORT).show();

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 20, 0);

        recyclerview=findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        viewModel= ViewModelProviders.of(RecyclerMain.this).get(HeroesViewModel.class);

        viewModel.getHero().observe(this, new Observer<List<Hero>>() {

            @Override
            public void onChanged(List<Hero> heroes) {

                adapter=new HeroesAdapter(RecyclerMain.this,heroes);
                recyclerview.setAdapter(adapter);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "OnStop", Toast.LENGTH_SHORT).show();
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,volume,0);
    }
}