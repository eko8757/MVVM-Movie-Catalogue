package com.example.moviemvvm.data.source.remote.response

import com.example.moviemvvm.data.model.DiscoverTv
import com.google.gson.annotations.SerializedName

data class TvResponse(
    @SerializedName("results")
    val result: MutableList<DiscoverTv>
)