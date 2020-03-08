package com.example.moviemvvm.utils

import android.util.Log
import com.example.moviemvvm.data.source.remote.response.MovieResponse
import com.example.moviemvvm.data.source.remote.response.TvResponse
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

object DataDummy {
    private var gson = Gson()

    fun generateFromJsonMovie() =
        gson.fromJson(loadJSON("movies.json"), MovieResponse::class.java).result

    fun generateFromJsonTv() =
        gson.fromJson(loadJSON("tv.json"), TvResponse::class.java).result

    private fun loadJSON(fileSource: String) : String? {
        var json: String? = null
        try {
            val input: InputStream = this.javaClass.classLoader!!.getResourceAsStream(fileSource)
            val size = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            json = String(buffer, charset("UTF-8"))
        }catch (e: IOException) {
            Log.e("Dummy", e.localizedMessage)
        }

        return json
    }
}