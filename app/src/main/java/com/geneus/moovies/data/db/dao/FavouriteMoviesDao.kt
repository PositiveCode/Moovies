package com.geneus.moovies.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geneus.moovies.data.db.model.MovieModel

@Dao
interface FavouriteMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieModel: MovieModel)

    @Query("DELETE FROM favourite_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM favourite_table")
    suspend fun getAllFavMovies(): List<MovieModel>?
}