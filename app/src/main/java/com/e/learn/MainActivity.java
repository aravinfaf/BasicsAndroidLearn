package com.e.learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    CoordinatorLayout coordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinator=findViewById(R.id.coordinator);
    }
}
