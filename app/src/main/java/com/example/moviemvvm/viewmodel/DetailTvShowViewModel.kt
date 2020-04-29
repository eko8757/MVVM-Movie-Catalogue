package com.example.moviemvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviemvvm.data.model.DiscoverTv
import com.example.moviemvvm.data.source.CatalogueRepository

class DetailTvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    var tvShowId = 0

    fun getTvShow(): LiveData<DiscoverTv> {
        return catalogueRepository.getTvShow(tvShowId)
    }

    fun addFavoriteTv(tvShow: DiscoverTv) {
        catalogueRepository.addFavoriteTvShow(tvShow)
    }

    fun removeFavoriteTv(tvShow: DiscoverTv) {
        catalogueRepository.removeFavoriteTvShow(tvShow)
    }

    fun isFavoriteTv(tvShow: DiscoverTv) : Boolean {
        return catalogueRepository.isFavoriteTvShow(tvShow)
    }
}