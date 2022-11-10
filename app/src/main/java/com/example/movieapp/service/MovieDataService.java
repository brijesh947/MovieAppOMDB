package com.example.movieapp.service;

import com.example.movieapp.model.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

    @GET("movie/popular")
    Observable<Movie> getPopularMoviesWithRx(@Query("api_key") String apiKey);
}
