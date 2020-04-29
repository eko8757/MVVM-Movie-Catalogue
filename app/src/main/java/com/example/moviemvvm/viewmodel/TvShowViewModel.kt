package com.example.moviemvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviemvvm.data.model.DiscoverTv
import com.example.moviemvvm.data.source.CatalogueRepository

class TvShowViewModel(private val catalogueRepository: CatalogueRepository): ViewModel() {

    var page = 1
    fun getTvShows(): LiveData<List<DiscoverTv>>{
        return catalogueRepository.getAllTvShow(page)
    }
}