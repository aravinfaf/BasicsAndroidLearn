package com.e.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.widget.ImageView;

public class FlipImageAnimation extends AppCompatActivity {

    AnimatorSet set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_image_animation);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ImageView imgView=(ImageView)findViewById(R.id.imageview);

        set = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.flip);
        set.setTarget(imgView);
        set.start();
    }
}
