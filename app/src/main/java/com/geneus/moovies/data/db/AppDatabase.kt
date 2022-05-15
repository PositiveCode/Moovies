package com.geneus.moovies.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.geneus.moovies.data.db.dao.GenreDao
import com.geneus.moovies.data.db.model.Genre

@Database(
    entities = arrayOf(
        Genre::class,
    ), version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun genreDao(): GenreDao

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