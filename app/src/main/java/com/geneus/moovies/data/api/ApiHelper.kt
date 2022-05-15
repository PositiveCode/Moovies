package com.geneus.moovies.data.api

import com.geneus.moovies.data.api.model.GenreResponse
import com.geneus.moovies.data.api.model.MoviesResponse

interface ApiHelper {
    suspend fun getNowPlayingMovies(page: Int = 1): Result<MoviesResponse>
    suspend fun getPopularMovies(page: Int = 1): Result<MoviesResponse>
    suspend fun getMovieGenre(): Result<GenreResponse>
}