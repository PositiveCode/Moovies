package com.geneus.moovies.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geneus.moovies.data.db.model.ApiSource
import com.geneus.moovies.data.db.model.Movie

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie)

    @Query("DELETE FROM movies_table")
    fun deleteAll()

    @Query("SELECT * FROM movies_table WHERE apiSource LIKE :apiSource")
    fun getMoviesByApiSource(apiSource: ApiSource): LiveData<List<Movie>?>
}