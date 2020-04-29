package com.example.moviemvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviemvvm.data.model.DiscoverMovie
import com.example.moviemvvm.data.source.CatalogueRepository

class DetailMovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    var movieId = 0

    fun getMovie() : LiveData<DiscoverMovie>{
        return catalogueRepository.getMovie(movieId)
    }

    fun addFavoriteMovie(movie: DiscoverMovie) {
        catalogueRepository.addFavoriteMovie(movie)
    }

    fun removeFavoriteMovie(movie: DiscoverMovie) {
        catalogueRepository.removeFavoriteMovie(movie)
    }

    fun isFavoriteMovie(movie: DiscoverMovie): Boolean {
        return catalogueRepository.isFavoritedMovie(movie)
    }
}