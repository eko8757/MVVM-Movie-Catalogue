package com.example.moviemvvm.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviemvvm.data.model.DiscoverMovie
import com.example.moviemvvm.data.model.DiscoverTv
import com.example.moviemvvm.data.source.local.LocalRepository
import com.example.moviemvvm.data.source.remote.RemoteRepository

class CatalogueRepository(private val remoteRepository: RemoteRepository, private val localRepository: LocalRepository): CatalogueDataSource {

    companion object{
        fun getInstance(remoteRepository: RemoteRepository, localRepository: LocalRepository): CatalogueRepository {
            return CatalogueRepository(remoteRepository, localRepository)
        }
    }

    override fun getAllMovies(page: Int): LiveData<List<DiscoverMovie>> {
        return remoteRepository.getMovies(page)
    }

    override fun getMovie(id: Int): LiveData<DiscoverMovie> {
        return remoteRepository.getMovie(id)
    }

    override fun getAllTvShow(page: Int): LiveData<List<DiscoverTv>> {
        return remoteRepository.getTvShows(page)
    }

    override fun getTvShow(id: Int): LiveData<DiscoverTv> {
        return remoteRepository.getTvShow(id)
    }

    override fun getAllFavoriteMovies(): LiveData<PagedList<DiscoverMovie>> {
        return LivePagedListBuilder(localRepository.getFavoriteMoviesPaged(), 10).build()
    }

    override fun addFavoriteMovie(movie: DiscoverMovie) {
        localRepository.addFavoriteMovie(movie)
    }

    override fun removeFavoriteMovie(movie: DiscoverMovie) {
        localRepository.removeFavoriteMovie(movie)
    }

    override fun isFavoritedMovie(movie: DiscoverMovie): Boolean {
        return localRepository.isFavoritedMovie(movie)
    }

    override fun getAllFavoriteTvShows(): LiveData<PagedList<DiscoverTv>> {
        return LivePagedListBuilder(localRepository.getFavoriteTvShowPaged(), 10).build()
    }

    override fun addFavoriteTvShow(tvShow: DiscoverTv) {
        localRepository.addFavoriteTvShow(tvShow)
    }

    override fun removeFavoriteTvShow(tvShow: DiscoverTv) {
        localRepository.removeFavoriteTvShow(tvShow)
    }

    override fun isFavoriteTvShow(tvShow: DiscoverTv): Boolean {
        return localRepository.isFavoritedTvShow(tvShow)
    }


}