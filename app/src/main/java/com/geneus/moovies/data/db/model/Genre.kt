package com.geneus.moovies.data.db.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre_table")
data class Genre(
    @PrimaryKey
    @NonNull
    val id: Int,
    @ColumnInfo(name = "name") val name: String?
)