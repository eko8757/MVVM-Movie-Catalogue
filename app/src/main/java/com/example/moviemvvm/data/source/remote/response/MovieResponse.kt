package com.example.moviemvvm.data.source.remote.response

import com.example.moviemvvm.data.model.DiscoverMovie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val result: MutableList<DiscoverMovie>
)