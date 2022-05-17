package com.geneus.moovies.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geneus.moovies.data.db.model.Genre

@Dao
interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(genre: Genre)

    @Query("DELETE FROM genre_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM genre_table WHERE id = :id")
    suspend fun getGenreById(id: String): Genre

    @Query("SELECT * FROM genre_table")
    suspend fun getAllGenre(): List<Genre>?
}