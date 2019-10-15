package com.e.learn.pagination;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private String title;

    public Movie() {
    }

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Creating 10 dummy content for list.
     *
     * @param itemCount
     * @return
     */
    public static List<Movie> createMovies(int itemCount) {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Movie movie = new Movie("Movie " + (itemCount == 0 ?
                    (itemCount + 1 + i) : (itemCount + i)));
            movies.add(movie);
        }
        return movies;
    }
}
