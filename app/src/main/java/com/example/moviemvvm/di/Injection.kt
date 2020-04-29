package com.example.moviemvvm.di

import android.app.Application
import com.example.moviemvvm.data.source.CatalogueRepository
import com.example.moviemvvm.data.source.local.LocalRepository
import com.example.moviemvvm.data.source.remote.RemoteRepository

class Injection {
    companion object {
        fun provideRepository(application: Application): CatalogueRepository {
            val remoteRepository = RemoteRepository.getInstance()
            val localRepository = LocalRepository.getInstance(application)
            return CatalogueRepository.getInstance(remoteRepository, localRepository)
        }
    }
}