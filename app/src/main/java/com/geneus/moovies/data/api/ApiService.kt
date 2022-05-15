package com.geneus.moovies.data.api

import com.geneus.moovies.data.api.model.GenreResponse
import com.geneus.moovies.data.api.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list?api_key=0e7274f05c36db12cbe71d9ab0393d47")
    suspend fun getMovieGenre(): Result<GenreResponse>

    @GET("movie/now_playing?api_key=0e7274f05c36db12cbe71d9ab0393d47")
    suspend fun getNowPlayingMovies(@Query("page") page: Int): Result<MoviesResponse>

    @GET("movie/popular?api_key=0e7274f05c36db12cbe71d9ab0393d47")
    suspend fun getPopularMovies(@Query("page") page: Int): Result<MoviesResponse>

    @GET("movie/top_rated?api_key=0e7274f05c36db12cbe71d9ab0393d47")
    suspend fun getTopRatedMovies(@Query("page") page: Int): Result<MoviesResponse>

    @GET("movie/upcoming?api_key=0e7274f05c36db12cbe71d9ab0393d47")
    suspend fun getUpcomingMovies(@Query("page") page: Int): Result<MoviesResponse>

}