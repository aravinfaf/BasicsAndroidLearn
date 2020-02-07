package com.e.learn.multimagepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ImageView;

import com.e.learn.R;

import java.lang.reflect.Field;
import java.util.Random;

public class RandomImage extends AppCompatActivity {

    ImageView iv;

    int []drarray= new int[]{R.drawable.buttonbg, R.drawable.background,R.drawable.ic_add_24dp,R.drawable.ic_close,R.drawable.diet};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_image);

        iv=findViewById(R.id.iv);

        TypedArray img=getResources().obtainTypedArray(R.array.apptour);
        Random random=new Random();
        int rndInt=random.nextInt(img.length());
        int resID=img.getResourceId(rndInt,0);
        //iv.setImageResource(resID);

        iv.setImageResource(drarray[random.nextInt(drarray.length)]);

    }
}
