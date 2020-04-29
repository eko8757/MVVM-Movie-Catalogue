package com.example.moviemvvm.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviemvvm.R
import com.example.moviemvvm.data.model.DiscoverMovie
import com.example.moviemvvm.utils.Constant
import com.example.moviemvvm.utils.gone
import com.example.moviemvvm.utils.visible
import com.example.moviemvvm.viewmodel.DetailMovieViewModel
import com.example.moviemvvm.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    private var movieId: Int = 0
    private lateinit var viewModel: DetailMovieViewModel
    private lateinit var mMovies: DiscoverMovie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        progressBar.visible()

        movieId = intent.getIntExtra("movieID", 0)

        viewModel = obtainViewModel(this)
        viewModel.movieId = movieId

        viewModel.getMovie().observe(this, Observer { movie ->
            movie?.let {
                mMovies = movie
                tvTitle.text = movie.title
                tvYear.text = movie.releaseDate
                tvDescription.text = movie.description
                ratingBar.rating = (movie.vote / 2)

                Picasso.get().load(Constant.BASE_IMAGE_URL + movie.backdrop).into(imgBackdrop)
                Picasso.get().load(Constant.BASE_IMAGE_URL + movie.poster).into(imgPoster)

                progressBar.gone()
                favoriteState()
            }
        })

        fabFavorite.setOnClickListener {
            fabOnClick()
        }
    }

    private fun obtainViewModel(detailMovieActivity: DetailMovieActivity): DetailMovieViewModel {
        val factory = ViewModelFactory.getInstance(detailMovieActivity.application)
        return ViewModelProviders.of(detailMovieActivity, factory).get(DetailMovieViewModel::class.java)
    }

    private fun fabOnClick() {
        if (viewModel.isFavoriteMovie(mMovies)) {
            viewModel.removeFavoriteMovie(mMovies)
            Snackbar.make(scrollView, getString(R.string.unfavorited, mMovies.title), Snackbar.LENGTH_SHORT).show()
            fabFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_disable))
        } else {
            viewModel.addFavoriteMovie(mMovies)
            Snackbar.make(scrollView, getString(R.string.favorited, mMovies.title), Snackbar.LENGTH_SHORT).show()
            fabFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_black_24dp))
        }
    }

    private fun favoriteState() {
        if (viewModel.isFavoriteMovie(mMovies)) {
            fabFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_black_24dp))
        } else {
            fabFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_disable))
        }
    }
}
