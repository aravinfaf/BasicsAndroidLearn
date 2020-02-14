package com.e.learn.multimagepicker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.e.learn.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomImages extends AppCompatActivity {

    Button shuffle;
    int ivArray[]=new int[]{R.id.iv,R.id.iv1,R.id.iv2,R.id.iv3};
    int imgaeArray[]=new int[]{R.drawable.rectangle_border,R.drawable.rzp_logo,R.drawable.diet,R.drawable.background,
                                R.drawable.buttonbg,R.drawable.ic_add_24dp,R.drawable.ic_close,R.drawable.ride_logo,
                                R.drawable.login_button_bk};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_image);

        shuffle=findViewById(R.id.shuffle);

        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random random=new Random(System.currentTimeMillis());
                List<Integer> generated=new ArrayList<>();

                for(int i=0;i<ivArray.length;i++){
                    int v=ivArray[i];
                    int next=random.nextInt(8)+1;

                    if(!generated.contains(next)){

                        generated.add(next);
                        ImageView iv=findViewById(v);
                        iv.setImageResource(imgaeArray[next]);
                    }
                    else {
                        i--;
                    }
                }
            }
        });
    }
}
