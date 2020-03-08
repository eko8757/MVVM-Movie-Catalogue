package com.example.moviemvvm.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviemvvm.data.model.DiscoverMovie
import com.example.moviemvvm.data.model.DiscoverTv
import com.example.moviemvvm.data.source.local.dao.MovieDao
import com.example.moviemvvm.data.source.local.dao.TvDao

@Database(entities = [DiscoverMovie::class, DiscoverTv::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvDao

    companion object{
        private const val DB_NAME = "movie_db"
        private var dbInstance: AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase {
            if (dbInstance == null) {
                dbInstance = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .build()
            }
            return dbInstance as AppDatabase
        }
    }
}