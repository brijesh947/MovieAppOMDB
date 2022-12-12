package com.example.movieapp.adpaters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.movieapp.R
import androidx.databinding.DataBindingUtil
import com.example.movieapp.databinding.MovieDetailBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieList
import com.example.movieapp.service.RetrofitInstance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieAdpater : RecyclerView.Adapter<MovieAdpater.ViewHolder> {
    var context: Context
    var movie: MutableList<Movie>? = null


    constructor(context: Context) {
        this.context = context
        movie = mutableListOf()
    }


    //    companion object {
//        private var movieAdpater: MovieAdpater? = null;
//
//        @Synchronized
//        operator fun invoke(context: Context, movie: MovieList): MovieAdpater {
//            if (movieAdpater == null) {
//                movieAdpater = MovieAdpater(context, movie)
//            } else {
//             //   Log.d("MainFragment", ": invoke Adapter's new Fragment is not created")
//
//            }
//            return movieAdpater as MovieAdpater
//        }
//    }
//
    @SuppressLint("notifyItemRangeInserted")
    fun addMovies(m: MovieList?): Boolean {
        Log.d("MainFragment", "addMovies: movieList's size " + m!!.Search.size)
        var temp = mutableListOf<Movie>()
        m!!.Search.forEach {
            Log.d("MainFragment", "addMovies: movie name " + it.Title)
            Log.d("xxxx", "isMovie Added: " + temp.add(it))
        }
        var length: Int = movie!!.size
        movie!!.addAll(temp)
        notifyItemRangeInserted(length, 10)
        Log.d("MainFragment", "addMovies: movie size " + movie!!.size)
        return true
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.movie_detail,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.movie = movie!!.get(position);
    }

    override fun getItemCount(): Int {
        Log.d("MainFragment", "getItemCount: " + movie!!.size)
        return movie!!.size
    }

    inner class ViewHolder(val binding: MovieDetailBinding) : RecyclerView.ViewHolder(binding.root)
}