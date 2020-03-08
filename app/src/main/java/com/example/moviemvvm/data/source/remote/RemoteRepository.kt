package com.example.moviemvvm.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviemvvm.data.model.DiscoverMovie
import com.example.moviemvvm.data.model.DiscoverTv
import com.example.moviemvvm.data.source.remote.response.MovieResponse
import com.example.moviemvvm.data.source.remote.response.TvResponse
import com.example.moviemvvm.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "RemoteRepository"
class RemoteRepository {
    private val apiClient = ApiClient.getApiClient().create(ApiInterface::class.java)

    companion object {
        fun getInstance(): RemoteRepository {
            return RemoteRepository()
        }
    }

    //movie
    fun getMovies(page: Int) : LiveData<List<DiscoverMovie>> {
        val movies : MutableLiveData<List<DiscoverMovie>> = MutableLiveData()

        EspressoIdlingResource.increment()

        apiClient.movies(page).enqueue(
            object : Callback<MovieResponse>{
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e(TAG, t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    response.body()?.let { movies.postValue(it.result) }
                    EspressoIdlingResource.decrement()
                }
            }
        )
        return movies
    }

    fun getMovie(id: Int) : LiveData<DiscoverMovie>{
        val movie : MutableLiveData<DiscoverMovie> = MutableLiveData()
        EspressoIdlingResource.increment()

        apiClient.movie(id).enqueue(
            object : Callback<DiscoverMovie>{
                override fun onFailure(call: Call<DiscoverMovie>, t: Throwable) {
                    Log.e(TAG, t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<DiscoverMovie>,
                    response: Response<DiscoverMovie>
                ) {
                    movie.postValue(response.body())
                    EspressoIdlingResource.decrement()
                }
            }
        )
        return movie
    }

    //tv
    fun getTvShows(page: Int) : LiveData<List<DiscoverTv>>{
        val tvShows: MutableLiveData<List<DiscoverTv>> = MutableLiveData()

        EspressoIdlingResource.increment()

        apiClient.tvShows(page).enqueue(
            object : Callback<TvResponse>{
                override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                    Log.e(TAG, t.localizedMessage)
                }

                override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                    response.body()?.let { tvShows.postValue(it.result) }
                    EspressoIdlingResource.decrement()
                }
            }
        )
        return tvShows
    }

    fun getTvShow(id: Int) : LiveData<DiscoverTv>{
        val tvShow: MutableLiveData<DiscoverTv> = MutableLiveData()

        EspressoIdlingResource.increment()

        apiClient.tvShow(id).enqueue(
            object : Callback<DiscoverTv>{
                override fun onFailure(call: Call<DiscoverTv>, t: Throwable) {
                    Log.e(TAG, t.localizedMessage)
                }

                override fun onResponse(call: Call<DiscoverTv>, response: Response<DiscoverTv>) {
                    tvShow.postValue(response.body())
                    EspressoIdlingResource.decrement()
                }
            }
        )
        return tvShow
    }
}