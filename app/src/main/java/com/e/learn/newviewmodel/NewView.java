package com.e.learn.newviewmodel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.e.learn.R;

public class NewView extends AppCompatActivity implements View.OnClickListener {

    TextView tvScoreA, tvScoreB;
    Button btnPlayerA, btnPlayerB;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newviewmodel);

        initView();

        mainViewModel= ViewModelProviders.of(NewView.this).get(MainViewModel.class);
        tvScoreA.setText(mainViewModel.getInitialCountA()+"");
        tvScoreB.setText(mainViewModel.getInitialCountB()+"");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnPlayerA:
                tvScoreA.setText(mainViewModel.getCurrentCountA()+"");
                break;
            case R.id.btnPlayerB:
                tvScoreB.setText(mainViewModel.getCurrentCountB()+"");
                break;
        }
    }

    private void initView() {
        // initialized all views here
        tvScoreA = findViewById(R.id.tvScorePlayerA);
        tvScoreB = findViewById(R.id.tvScorePlayerB);
        btnPlayerA = findViewById(R.id.btnPlayerA);
        btnPlayerB = findViewById(R.id.btnPlayerB);
        btnPlayerA.setOnClickListener(this);
        btnPlayerB.setOnClickListener(this);

    }
}
