package com.example.movieapp.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.movieapp.R
import com.example.movieapp.adpaters.MovieAdpater
import com.example.movieapp.service.MoviesRepository
import com.example.movieapp.service.RetrofitInstance
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    private lateinit var mViewModel: MainViewModel
    private lateinit var factory: MoviesViewModelFactory
    private lateinit var movieAdpater: MovieAdpater
    private var current: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_main, container, false)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = RetrofitInstance()
        val repository = MoviesRepository(api)
        factory = MoviesViewModelFactory(repository)
        mViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        mViewModel.getMovies(current.toString())
        movieAdpater = MovieAdpater(requireContext())
        recycle_view.layoutManager = GridLayoutManager(requireContext(), 3)
        mViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            recycle_view.also {
                Log.d("Movies", "onActivity Created: movie name $movies")
                movieAdpater.addMovies(movies)
                it.adapter = movieAdpater
            }

        })

        recycle_view.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isLastVisible() && dy > 1) {
                    Log.d("MainFragment", "onScrolled:  Last Item reached")
                    current++
                    mViewModel.getMovies(current.toString())

                }


            }
        })


    }

    fun isLastVisible(): Boolean {
        val layoutManager = recycle_view.layoutManager as LinearLayoutManager
        val pos = layoutManager.findLastCompletelyVisibleItemPosition()
        val numItems: Int = recycle_view.adapter!!.itemCount
        return pos >= numItems - 1
    }

}