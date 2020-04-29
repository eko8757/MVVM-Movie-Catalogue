package com.example.moviemvvm.ui.movie


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.moviemvvm.R
import com.example.moviemvvm.adapter.MovieAdapter
import com.example.moviemvvm.data.model.DiscoverMovie
import com.example.moviemvvm.ui.detail.DetailMovieActivity
import com.example.moviemvvm.utils.InfiniteScrollListener
import com.example.moviemvvm.utils.gone
import com.example.moviemvvm.utils.visible
import com.example.moviemvvm.viewmodel.MovieViewModel
import com.example.moviemvvm.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie_list.*

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter
    private var page = 1
    private var mMovies: MutableList<DiscoverMovie> = mutableListOf()

    companion object {
        fun newInstance(): Fragment {
            return MovieListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            viewModel = obtainViewModel(requireActivity())
            adapter = MovieAdapter(context!!) {
              val i = Intent(context, DetailMovieActivity::class.java)
                i.putExtra("movieID", it.id)
                startActivity(i)
            }

            loadMovies()
            rv_movie.adapter = adapter
            rv_movie.layoutManager = LinearLayoutManager(context)
            rv_movie.setHasFixedSize(true)
        }

        rv_movie.addOnScrollListener(scrollData())
    }

    private fun obtainViewModel(requireActivity: FragmentActivity): MovieViewModel {
        val factory = ViewModelFactory.getInstance(requireActivity.application)
        return ViewModelProviders.of(requireActivity, factory).get(MovieViewModel::class.java)
    }

    private fun loadMovies() {
        progress_bar.visible()
        viewModel.page = page
        viewModel.getMovies().observe(this, Observer { movie ->
            movie?.let {
                mMovies.addAll(movie)
                adapter.setMovies(mMovies)
                progress_bar.gone()
            }
        })
    }

    private fun scrollData(): InfiniteScrollListener {
        return object : InfiniteScrollListener() {
            override fun onLoadMore() {
                page += 1
                loadMovies()
            }
        }
    }
}
