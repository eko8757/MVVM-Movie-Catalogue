package com.example.moviemvvm.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.moviemvvm.data.model.DiscoverMovie

@Dao
interface MovieDao {
    @get:Query("SELECT * FROM DiscoverMovie")
    val all: LiveData<List<DiscoverMovie>>

    @Query("SELECT * FROM DiscoverMovie")
    fun allAsPaged(): DataSource.Factory<Int, DiscoverMovie>

    @Query("SELECT * FROM DiscoverMovie WHERE id = :id")
    fun getById(id: Int?): DiscoverMovie?

    @Insert(onConflict = REPLACE)
    fun insert(movie: DiscoverMovie): Long

    @Delete
    fun delete(movie: DiscoverMovie)

    @Query("DELETE FROM DiscoverMovie WHERE id = :id")
    fun deleteById(id: Long): Int

    @Update
    fun update(movie: DiscoverMovie): Int
}