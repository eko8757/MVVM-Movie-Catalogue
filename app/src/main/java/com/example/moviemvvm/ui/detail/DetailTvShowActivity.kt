package com.example.moviemvvm.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviemvvm.R
import com.example.moviemvvm.data.model.DiscoverTv
import com.example.moviemvvm.utils.Constant
import com.example.moviemvvm.utils.gone
import com.example.moviemvvm.utils.visible
import com.example.moviemvvm.viewmodel.DetailTvShowViewModel
import com.example.moviemvvm.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity() {

    private var tvShowId: Int = 0
    private lateinit var viewModel: DetailTvShowViewModel
    private lateinit var mTvShows: DiscoverTv

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        progressBar.visible()

        tvShowId = intent.getIntExtra("tvShowID", 0)

        viewModel = obtainViewModel(this)
        viewModel.tvShowId = tvShowId

        viewModel.getTvShow().observe(this, Observer { tvShow ->
            tvShow?.let {
                mTvShows = tvShow

                tvTitle.text = tvShow.title
                tvYear.text = tvShow.firstAirDate
                tvDescription.text = tvShow.description
                ratingBar.rating = (tvShow.rating / 2)

                Picasso.get().load(Constant.BASE_IMAGE_URL + tvShow.backdrop).into(imgBackdrop)
                Picasso.get().load(Constant.BASE_IMAGE_URL + tvShow.poster).into(imgPoster)

                progressBar.gone()
                favoriteState()
            }
        })

        fabFavorite.setOnClickListener {
            fabOnClick()
        }
    }

    private fun obtainViewModel(detailTvShowActivity: DetailTvShowActivity): DetailTvShowViewModel {
        val factory = ViewModelFactory.getInstance(detailTvShowActivity.application)
        return ViewModelProviders.of(detailTvShowActivity, factory).get(DetailTvShowViewModel::class.java)
    }

    private fun fabOnClick() {
        if (viewModel.isFavoriteTv(mTvShows)) {
            viewModel.removeFavoriteTv(mTvShows)
            Snackbar.make(scrollView, getString(R.string.unfavorited, mTvShows.title), Snackbar.LENGTH_SHORT).show()
            fabFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_disable))
        } else {
            viewModel.addFavoriteTv(mTvShows)
            Snackbar.make(scrollView, getString(R.string.favorited, mTvShows.title), Snackbar.LENGTH_SHORT).show()
            fabFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_black_24dp))
        }
    }


    private fun favoriteState() {
        if (viewModel.isFavoriteTv(mTvShows)) {
            fabFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_black_24dp))
        } else {
            fabFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_disable))
        }
    }

}
