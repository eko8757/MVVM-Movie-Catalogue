package com.example.moviemvvm.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.moviemvvm.data.model.DiscoverTv

@Dao
interface TvDao {
    @get:Query("SELECT * FROM DiscoverTv")
    val all: LiveData<List<DiscoverTv>>

    @Query("SELECT * FROM DiscoverTv")
    fun allAsPaged(): DataSource.Factory<Int, DiscoverTv>

    @Query("SELECT * FROM DiscoverTv WHERE id = :id")
    fun getById(id: Int?): DiscoverTv?

    @Insert(onConflict = REPLACE)
    fun insert(tvShow: DiscoverTv)

    @Delete
    fun delete(tvShow: DiscoverTv)
}