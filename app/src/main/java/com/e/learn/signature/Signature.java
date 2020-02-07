package com.e.learn.signature;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.e.learn.R;

public class Signature extends AppCompatActivity {

    LinearLayout linear;
    Button save,clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature2);

        linear=findViewById(R.id.linear);
        save=findViewById(R.id.save);
        clear=findViewById(R.id.clear);

        CaptureSignatureView mSig = new CaptureSignatureView(this, null);
        linear.addView(mSig, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("EEE",mSig.getBytes().length+"");


            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSig.ClearCanvas();
            }
        });
    }
}
