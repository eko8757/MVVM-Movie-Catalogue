package com.example.moviemvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviemvvm.data.model.DiscoverMovie
import com.example.moviemvvm.data.source.CatalogueRepository

class MovieViewModel(private val catalogueRepository: CatalogueRepository): ViewModel() {

    var page = 1

    fun getMovies() : LiveData<List<DiscoverMovie>> {
        return catalogueRepository.getAllMovies(page)
    }
}