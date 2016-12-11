package com.gmail.aziz.mouawad.john.johnmovieapp;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Jo on 10/31/2016.
 */
public interface TopMovieApi {

    @GET("/movie/top_rated")
    void getPopularMovies(Callback<Movie.TopMovieResult> cb);


}

