package com.e.learn.screensizes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.e.learn.R;

public class DifferentScreensActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_screens);

        int x=5;
       // System.out.println(x++  +","+ ++x);
        Log.e("OOO",x++  +","+ ++x);
    }
}
