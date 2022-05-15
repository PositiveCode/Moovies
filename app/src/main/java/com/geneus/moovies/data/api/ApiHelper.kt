package com.geneus.moovies.data.api

import com.geneus.moovies.data.api.model.GenreResponse
import com.geneus.moovies.data.api.model.NowPlayingMoviesResponse

interface ApiHelper {
    suspend fun getNowPlayingMovies(page: Int): Result<NowPlayingMoviesResponse>
    suspend fun getMovieGenre(): Result<GenreResponse>
}