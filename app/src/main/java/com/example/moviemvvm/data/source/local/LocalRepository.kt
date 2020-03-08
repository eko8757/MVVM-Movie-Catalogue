package com.example.moviemvvm.data.source.local

import android.content.Context
import androidx.paging.DataSource
import com.example.moviemvvm.data.model.DiscoverMovie
import com.example.moviemvvm.data.model.DiscoverTv
import com.example.moviemvvm.data.source.local.dao.MovieDao
import com.example.moviemvvm.data.source.local.dao.TvDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalRepository(context: Context) {

    private val movieDao: MovieDao
    private val tvDao: TvDao

    init {
        val db = AppDatabase.getDatabase(context)
        movieDao = db.movieDao()
        tvDao = db.tvShowDao()
    }

    companion object {
        fun getInstance(context: Context) : LocalRepository {
            return LocalRepository(context)
        }
    }

    fun getFavoriteMoviesPaged(): DataSource.Factory<Int, DiscoverMovie> {
        return movieDao.allAsPaged()
    }

    fun addFavoriteMovie(movie: DiscoverMovie) {
        GlobalScope.launch(Dispatchers.Main) { movieDao.insert(movie) }
    }

    fun removeFavoriteMovie(movie: DiscoverMovie) {
        GlobalScope.launch(Dispatchers.Main) { movieDao.delete(movie) }
    }

    fun isFavoritedMovie(movie: DiscoverMovie): Boolean {
        return movieDao.getById(movie.id) != null
    }

    fun getFavoriteTvShowPaged(): DataSource.Factory<Int, DiscoverTv> {
        return tvDao.allAsPaged()
    }

    fun addFavoriteTvShow(tvShow: DiscoverTv) {
        GlobalScope.launch(Dispatchers.Main) { tvDao.insert(tvShow) }
    }

    fun removeFavoriteTvShow(tvShow: DiscoverTv) {
        GlobalScope.launch(Dispatchers.Main) { tvDao.delete(tvShow) }
    }

    fun isFavoritedTvShow(tvShow: DiscoverTv): Boolean {
        return tvDao.getById(tvShow.id) != null
    }
}