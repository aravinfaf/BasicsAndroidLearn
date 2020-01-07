package com.e.learn.dagger_retrofit;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.e.learn.R;
import com.e.learn.recyclerviewmodel.Api;
import com.e.learn.recyclerviewmodel.Hero;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainDaggerRetrofit extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    ListView recyclerView;

    MyApplication myApplication;

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dagger_retrofit);
        ButterKnife.bind(MainDaggerRetrofit.this);
        ((MyApplication)getApplication()).getApiComponent().inject(this);
        
        getHeros();
    }

    private void getHeros() {

        Api api=retrofit.create(Api.class);
        Call<List<Hero>> call=api.getHeroes();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                if(response.isSuccessful()){
                    List<Hero> heroList=response.body();

                    String hero[]=new String[heroList.size()];

                    for(int i=0;i<heroList.size();i++){
                        hero[i]=heroList.get(i).getName();
                    }
                    recyclerView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, hero));
                }
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(MainDaggerRetrofit.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
