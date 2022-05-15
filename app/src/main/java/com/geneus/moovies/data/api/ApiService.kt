package com.geneus.moovies.data.api

import com.geneus.moovies.data.api.model.GenreResponse
import com.geneus.moovies.data.api.model.NowPlayingMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing?api_key=0e7274f05c36db12cbe71d9ab0393d47")
    suspend fun getNowPlayingMovies(@Query("page") page: Int): Result<NowPlayingMoviesResponse>

    @GET("genre/movie/list?api_key=0e7274f05c36db12cbe71d9ab0393d47")
    suspend fun getMovieGenre(): Result<GenreResponse>
}