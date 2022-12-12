package com.example.movieapp.service

class MoviesRepository(
    private val api: RetrofitInstance
) : SafeApiRequest() {

    suspend fun getProperties(page:String) = apiRequest { api.getMovies(page)}

}