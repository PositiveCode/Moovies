package com.geneus.moovies.data.repo

import androidx.lifecycle.LiveData
import com.geneus.moovies.data.api.ApiHelper
import com.geneus.moovies.data.api.model.Movie
import com.geneus.moovies.data.db.dao.GenreDao
import com.geneus.moovies.data.db.model.Genre
import com.geneus.moovies.utils.Resource

class MovieRepo(
    private val apiHelper: ApiHelper,
    private val genreDao: GenreDao,
) {
    suspend fun getMovieGenre(): LiveData<List<Genre>?> {
        apiHelper.getMovieGenre().onSuccess {
            if (it.genreList.isNotEmpty()) {
                for (genre in it.genreList) {
                    addGenre(genre)
                }
            }
        }

        return genreDao.getAllGenre()
    }

    suspend fun getMoviesBySource(category: Category): Resource<List<Movie>> {
        /**
         * ApiSource is base on the fragment:
         * - NOW_PLAYING
         * - POPULAR
         * - SEARCH
         * - TOP_RATED
         * - UPCOMING
         * */
        when (category) {
            Category.NOW_PLAYING -> apiHelper.getNowPlayingMovies()
            Category.POPULAR -> apiHelper.getPopularMovies()
            Category.TOP_RATED -> apiHelper.getTopRatedMovies()
            Category.UPCOMING -> apiHelper.getUpcomingMovies()
        }.onSuccess {
            /**
             * Returns a resource with success status
             * */
            return Resource.success(it.moviesList)
        }.onFailure {
            /**
             * Returns a resource with error status and null as payload.
             * */
            return Resource.error(it.message ?: "Network issue detected.", null)
        }
        /**
         * Returns a resource with loading status and null as payload.
         * */
        return Resource.loading(null)
    }

    private suspend fun addGenre(genre: com.geneus.moovies.data.api.model.Genre) {
        /**
         * Convert genre from api to genre for db
         * */
        val genreModel = Genre(
            id = genre.id.toInt(),
            name = genre.name
        )
        genreDao.insert(genreModel)
    }

    enum class Category {
        NOW_PLAYING,
        POPULAR,
        TOP_RATED,
        UPCOMING
    }
}