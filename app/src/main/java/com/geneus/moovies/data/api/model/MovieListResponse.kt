package com.geneus.moovies.data.api.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("results")
    val moviesList: ArrayList<Movie>,
)

data class Dates(
    @SerializedName("maximum")
    val maximum: String,
    @SerializedName("minimum")
    val minimum: String,
)
