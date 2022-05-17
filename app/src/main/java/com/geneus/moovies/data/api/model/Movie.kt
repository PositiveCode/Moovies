package com.geneus.moovies.data.api.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Movie(
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    @SerializedName("genres")
    val genres: List<com.geneus.moovies.data.api.model.Genre>?,
    @SerializedName("id")
    val id: BigDecimal?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: BigDecimal?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: BigDecimal?,
    @SerializedName("vote_count")
    val voteCount: BigDecimal?,
    @SerializedName("tagline")
    val tagline: String?
)