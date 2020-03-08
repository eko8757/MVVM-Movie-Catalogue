package com.example.moviemvvm.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviemvvm.data.model.DiscoverMovie
import com.example.moviemvvm.data.model.DiscoverTv

interface CatalogueDataSource {

    fun getAllMovies(page: Int): LiveData<List<DiscoverMovie>>

    fun getMovie(id: Int): LiveData<DiscoverMovie>

    fun getAllTvShow(page: Int): LiveData<List<DiscoverTv>>

    fun getTvShow(id: Int): LiveData<DiscoverTv>

    fun getAllFavoriteMovies(): LiveData<PagedList<DiscoverMovie>>

    fun addFavoriteMovie(movie: DiscoverMovie)

    fun removeFavoriteMovie(movie: DiscoverMovie)

    fun isFavoritedMovie(movie: DiscoverMovie): Boolean

    fun getAllFavoriteTvShows(): LiveData<PagedList<DiscoverTv>>

    fun addFavoriteTvShow(tvShow: DiscoverTv)

    fun removeFavoriteTvShow(tvShow: DiscoverTv)

    fun isFavoriteTvShow(tvShow: DiscoverTv): Boolean
}