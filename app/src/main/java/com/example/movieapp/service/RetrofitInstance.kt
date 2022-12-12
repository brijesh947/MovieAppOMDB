package com.example.movieapp.service

import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieList

import retrofit2.Retrofit

import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInstance {


    @GET("?apikey=373c9964&s=*bal*")
    suspend fun getMovies(@Query("page")page: String) : Response<MovieList>


    @GET("?apikey=373c9964&s=a&type=movie")
    suspend fun getMoviesData() : Response<MovieList>

    @GET("?apikey=373c9964&s=*b*&type=series&page=1")
    suspend fun getSeriesData() : Response<MovieList>





    companion object {
//        private val moshi = Moshi.Builder()...
//            .add(KotlinJsonAdapterFactory())
//            .build()

      //  private var retrofit: Retrofit? = null
        private const val BASE_URL = "http://www.omdbapi.com/"
        @Synchronized
        operator fun invoke() : RetrofitInstance {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(RetrofitInstance::class.java)
        }
    }

}