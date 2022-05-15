package com.geneus.moovies.data.repo

import androidx.lifecycle.LiveData
import com.geneus.moovies.data.api.ApiHelper
import com.geneus.moovies.data.api.model.Result
import com.geneus.moovies.data.db.dao.GenreDao
import com.geneus.moovies.data.db.dao.MoviesDao
import com.geneus.moovies.data.db.model.ApiSource
import com.geneus.moovies.data.db.model.Genre
import com.geneus.moovies.data.db.model.Movie

class MovieRepo(
    private val apiHelper: ApiHelper,
    private val genreDao: GenreDao,
    private val moviesDao: MoviesDao,
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

    suspend fun getMoviesBySource(apiSource: ApiSource): LiveData<List<Movie>?> {
        when (apiSource) {
            ApiSource.NOW_PLAYING -> apiHelper.getNowPlayingMovies()
            else -> apiHelper.getPopularMovies()
        }.onSuccess {
            if (it.results.isNotEmpty()) {
                for (movie in it.results) {
                    addMovies(movie, apiSource)
                }
            }
        }

        return moviesDao.getMoviesByApiSource(apiSource)
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

    private suspend fun addMovies(movies: Result, apiSource: ApiSource) {
        val movie = Movie(
            id = movies.id.toInt(),
            apiSource = apiSource,
            adult = movies.adult,
            backdropPath = movies.backdropPath,
            genreIds = movies.genreIds,
            originalLanguage = movies.originalLanguage,
            originalTitle = movies.originalTitle,
            overview = movies.overview,
            popularity = movies.popularity.toDouble(),
            posterPath = movies.posterPath,
            releaseDate = movies.releaseDate,
            title = movies.title,
            video = movies.video,
            voteAverage = movies.voteAverage.toDouble(),
            voteCount = movies.voteCount.toDouble()
        )
        moviesDao.insert(movie)
    }
}