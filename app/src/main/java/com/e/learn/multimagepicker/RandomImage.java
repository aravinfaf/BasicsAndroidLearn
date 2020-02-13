package com.e.learn.multimagepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.e.learn.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomImage extends AppCompatActivity {

    Button shuffle;
    int id[]=new int[]{R.id.iv,R.id.iv1,R.id.iv2,R.id.iv3};

    int []drarray= new int[]{R.drawable.buttonbg, R.drawable.background,R.drawable.ic_add_24dp,R.drawable.ic_close,R.drawable.diet,
                        R.drawable.common_full_open_on_phone,R.drawable.googleg_disabled_color_18,R.drawable.rzp_logo,
                        R.drawable.rectangle_border};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_image);

        shuffle=findViewById(R.id.shuffle);

        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random random = new Random( System.currentTimeMillis() );
                List<Integer> generated = new ArrayList<Integer>();
                for (int i = 0; i < id.length; i++) {

                    int v = id[i];
                    int next = random.nextInt( 8 ) + 1;

                    if ( !generated.contains( next ) ) {
                        generated.add( next );
                        ImageView iv = (ImageView) findViewById( v );
                        iv.setImageResource( drarray[next] );
                    }
                    else {
                        i--;
                    }
                }
            }
        });
    }
}