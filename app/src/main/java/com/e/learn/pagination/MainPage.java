package com.e.learn.pagination;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.e.learn.R;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainPage extends AppCompatActivity  {

    private static final String TAG = "MainActivity";
    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 3;
    private int currentPage = PAGE_START;

    LinearLayoutManager linearLayoutManager;

    @BindView(R.id.main_recycler)
    RecyclerView rv;

    @BindView(R.id.main_progress)
    ProgressBar progressBar;

    PaginationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ButterKnife.bind(MainPage.this);

        adapter = new PaginationAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);

        rv.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public void loadMoreItems() {

                isLoading=true;
                currentPage+=1;

                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextpage();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }
        });
        // mocking network delay for API call
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadFirstpage();
            }
        }, 1000);
    }

    private void loadFirstpage(){

        List<Movie> movieList=Movie.createMovies(adapter.getItemCount());
        progressBar.setVisibility(View.GONE);
        adapter.addAll(movieList);

        if(currentPage<=TOTAL_PAGES)
            adapter.addloadingFooter();
        else
            isLastPage=true;
    }

    private void loadNextpage(){
        List<Movie> movieList=Movie.createMovies(adapter.getItemCount());
        adapter.removingFooter();
        isLoading=false;
        adapter.addAll(movieList);

        if(currentPage!=TOTAL_PAGES)
            adapter.addloadingFooter();
        else
            isLastPage=true;
    }
}