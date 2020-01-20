package com.e.learn.loading_button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.e.learn.R;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingButtonActivity extends AppCompatActivity {

    @BindView(R.id.cirLoadingButton)
    CircularProgressButton circularProgressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_button);
        ButterKnife.bind(LoadingButtonActivity.this);

        circularProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                circularProgressButton.startAnimation();

            }
        });
    }
}
