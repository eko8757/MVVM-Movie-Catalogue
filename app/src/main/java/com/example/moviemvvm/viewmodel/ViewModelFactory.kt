package com.example.moviemvvm.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviemvvm.data.source.CatalogueRepository
import com.example.moviemvvm.di.Injection

class ViewModelFactory(private val catalogueRepository: CatalogueRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(Injection.provideRepository(application))
                    }
                }

            }
            return INSTANCE
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(catalogueRepository) as (T)
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> DetailMovieViewModel(catalogueRepository) as (T)
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> TvShowViewModel(catalogueRepository) as (T)
            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) -> DetailTvShowViewModel(catalogueRepository) as (T)
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(catalogueRepository) as (T)
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}