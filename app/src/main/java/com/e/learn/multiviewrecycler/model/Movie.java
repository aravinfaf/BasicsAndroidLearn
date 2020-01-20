package com.e.learn.multiviewrecycler.model;

public class Movie {
    private String movieTitle;
    private String movieSubtitle;
    private String moviePictureURL;

    public Movie(String movieTitle, String movieSubtitle, String moviePictureURL) {
        this.movieTitle = movieTitle;
        this.movieSubtitle = movieSubtitle;
        this.moviePictureURL = moviePictureURL;
    }

    public Movie() {
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieSubtitle() {
        return movieSubtitle;
    }

    public void setMovieSubtitle(String movieSubtitle) {
        this.movieSubtitle = movieSubtitle;
    }

    public String getMoviePictureURL() {
        return moviePictureURL;
    }

    public void setMoviePictureURL(String moviePictureURL) {
        this.moviePictureURL = moviePictureURL;
    }
}
