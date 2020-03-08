package com.example.moviemvvm.data.source.remote

import com.example.moviemvvm.data.model.DiscoverMovie
import com.example.moviemvvm.data.model.DiscoverTv
import com.example.moviemvvm.data.source.remote.response.MovieResponse
import com.example.moviemvvm.data.source.remote.response.TvResponse
import com.example.moviemvvm.utils.Constant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    //movie
    @GET("movie/popular?api_key=${Constant.API_KEY}")
    fun movies(@Query("page") page: Int) : Call<MovieResponse>

    @GET("movie/{id}?api_key=${Constant.API_KEY}")
    fun movie(@Path("id") id: Int) : Call<DiscoverMovie>

    //tv show
    @GET("tv/popular?api_key=${Constant.API_KEY}")
    fun tvShows(@Query("page") page: Int) : Call<TvResponse>

    @GET("tv/{id}?api_key=${Constant.API_KEY}")
    fun tvShow(@Path("id") id: Int) : Call<DiscoverTv>
}