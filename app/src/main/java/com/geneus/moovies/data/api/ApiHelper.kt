package com.geneus.moovies.data.api

import com.geneus.moovies.data.api.model.GenreResponse
import com.geneus.moovies.data.api.model.Movie
import com.geneus.moovies.data.api.model.MovieListResponse

interface ApiHelper {
    suspend fun getNowPlayingMovies(page: Int = 1): Result<MovieListResponse>
    suspend fun getPopularMovies(page: Int = 1): Result<MovieListResponse>
    suspend fun getTopRatedMovies(page: Int = 1): Result<MovieListResponse>
    suspend fun getUpcomingMovies(page: Int = 1): Result<MovieListResponse>
    suspend fun getMoviesByQuery(query: String, page: Int = 1): Result<MovieListResponse>
    suspend fun getMovieById(movieId: Int = 1): Result<Movie>
    suspend fun getMovieGenre(): Result<GenreResponse>
}