package com.e.learn.recyclerviewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.e.learn.R;
import java.util.List;

public class RecyclerMain extends AppCompatActivity {

    RecyclerView recyclerview;
    HeroesViewModel viewModel;
    HeroesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_main);

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
}