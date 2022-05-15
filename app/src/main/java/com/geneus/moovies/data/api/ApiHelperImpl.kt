package com.geneus.moovies.data.api

import com.geneus.moovies.data.api.model.NowPlayingMoviesResponse

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getNowPlayingMovies(page: Int) = apiService.getNowPlayingMovies(page)
    override suspend fun getMovieGenre() = apiService.getMovieGenre()
}