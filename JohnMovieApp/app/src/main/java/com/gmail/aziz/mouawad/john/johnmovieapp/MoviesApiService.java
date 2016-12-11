package com.gmail.aziz.mouawad.john.johnmovieapp;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by jose on 10/6/15.
 */
public interface MoviesApiService {
    @GET("/discover/movie")
    void getPopularMovies(@Query("sort_by") String sort,Callback<Movie.MovieResult> cb);

    @GET("/movie/{id}/reviews")
    void getReviewsMovies(@Path("id") String id_for_reveiwe, Callback<Review.RevieweResult> cb);

    @GET("/movie/{id}/videos")
    void getMovieTrailers(@Path("id") String id_for_reveiwe, Callback<Trailer.TrailerResult> cb);
}
