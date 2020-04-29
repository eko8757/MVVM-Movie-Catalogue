package com.example.moviemvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviemvvm.data.source.CatalogueRepository

class FavoriteViewModel(private val catalogueRepository: CatalogueRepository): ViewModel() {

    fun getMovies() = catalogueRepository.getAllFavoriteMovies()
    fun getTvShows() = catalogueRepository.getAllFavoriteTvShows()
}