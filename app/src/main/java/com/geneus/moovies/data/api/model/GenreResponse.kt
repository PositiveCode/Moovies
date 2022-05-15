package com.geneus.moovies.data.api.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class GenreResponse(
    @SerializedName("genre")
    val genre: List<Genre>
)

data class Genre(
    @SerializedName("id")
    val id: BigDecimal,
    @SerializedName("name")
    val name: String,
)