package com.geneus.moovies.data.api

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getNowPlayingMovies(page: Int) = apiService.getNowPlayingMovies(page)
    override suspend fun getPopularMovies(page: Int) = apiService.getPopularMovies(page)
    override suspend fun getTopRatedMovies(page: Int) = apiService.getTopRatedMovies(page)
    override suspend fun getUpcomingMovies(page: Int) = apiService.getUpcomingMovies(page)
    override suspend fun getMovieById(movieId: Int) = apiService.getMovieById(movieId)
    override suspend fun getMovieGenre() = apiService.getMovieGenre()
}