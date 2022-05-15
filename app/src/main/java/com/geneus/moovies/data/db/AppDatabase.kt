package com.geneus.moovies.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.geneus.moovies.data.db.dao.GenreDao
import com.geneus.moovies.data.db.dao.MoviesDao
import com.geneus.moovies.data.db.model.DataConverter
import com.geneus.moovies.data.db.model.Genre
import com.geneus.moovies.data.db.model.Movie

@Database(
    entities = arrayOf(
        Genre::class,
        Movie::class
    ), version = 1, exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun genreDao(): GenreDao
    abstract fun moviesDao(): MoviesDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "moovies.db"
                    ).build()
                }
            }
            return instance!!
        }
    }
}