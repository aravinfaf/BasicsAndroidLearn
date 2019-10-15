package com.e.learn.recyclerviewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeroesViewModel extends ViewModel {

    private MutableLiveData<List<Hero>> herolist;

    public LiveData<List<Hero>> getHero(){

        if(herolist==null){
            herolist=new MutableLiveData<List<Hero>>();
            loadheros();
        }else{
            loadheros();
        }
        return herolist;
    }

    private void loadheros() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api=retrofit.create(Api.class);
        Call<List<Hero>> call=api.getHeroes();

        call.enqueue(new Callback<List<Hero>>() {

            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                if(response.isSuccessful()){
                    herolist.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {

            }
        });
    }
}
