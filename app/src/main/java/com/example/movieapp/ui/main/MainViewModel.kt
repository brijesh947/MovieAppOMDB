package com.example.movieapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieList
import com.example.movieapp.service.MoviesRepository
import com.example.movieapp.util.Coroutines
import kotlinx.coroutines.Job

class MainViewModel( private val repository: MoviesRepository) : ViewModel() {


    private lateinit var job: Job

    var movieData = MutableLiveData<MovieList>()


    val movies: LiveData<MovieList>
        get() = movieData
   var movieListData  = MutableLiveData<List<Movie>?>()

    fun getMovies(page:String) {
        job = Coroutines.ioThenMain(
            { repository.getProperties(page) },
            {
                movieData.value = it
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }

}




