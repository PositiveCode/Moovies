package com.geneus.moovies.data.api

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getNowPlayingMovies(page: Int) = apiService.getNowPlayingMovies(page)
    override suspend fun getPopularMovies(page: Int) = apiService.getPopularMovies(page)
    override suspend fun getMovieGenre() = apiService.getMovieGenre()
}