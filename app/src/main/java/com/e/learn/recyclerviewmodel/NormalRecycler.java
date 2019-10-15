package com.e.learn.recyclerviewmodel;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.e.learn.R;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NormalRecycler extends AppCompatActivity {

    RecyclerView recyclerview;
    List<Hero> heroList;
    HeroesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_main);

        recyclerview=findViewById(R.id.recyclerview);
        heroList=new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api=retrofit.create(Api.class);
        Call<List<Hero>> call=api.getHeroes();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                if (response.isSuccessful()) {
                    heroList=response.body();

                    adapter=new HeroesAdapter(NormalRecycler.this,heroList);
                    recyclerview.setLayoutManager(new LinearLayoutManager(NormalRecycler.this));
                    recyclerview.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {

            }
        });

    }
}
