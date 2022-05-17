package com.geneus.moovies.data.repo

import com.geneus.moovies.data.api.ApiHelper
import com.geneus.moovies.data.api.model.Movie
import com.geneus.moovies.data.db.dao.FavouriteMoviesDao
import com.geneus.moovies.data.db.dao.GenreDao
import com.geneus.moovies.data.db.model.Genre
import com.geneus.moovies.data.db.model.MovieModel
import com.geneus.moovies.utils.Resource

class MovieRepo(
    private val apiHelper: ApiHelper,
    private val genreDao: GenreDao,
    private val favouriteMoviesDao: FavouriteMoviesDao,
) {
    suspend fun getMovieGenre(): List<Genre>? {
        apiHelper.getMovieGenre().onSuccess {
            if (it.genreList.isNotEmpty()) {
                for (genre in it.genreList) {
                    addGenre(genre)
                }
            }
        }

        return genreDao.getAllGenre()
    }

    private suspend fun getMovieGenresLocally() = genreDao.getAllGenre()

    suspend fun getMoviesByCategory(category: Category, page: Int = 1): Resource<ArrayList<Movie>> {
        /**
         * Category base on fragment:
         * - NOW_PLAYING
         * - POPULAR
         * - SEARCH
         * - TOP_RATED
         * - UPCOMING
         * */
        when (category) {
            Category.NOW_PLAYING -> apiHelper.getNowPlayingMovies(page)
            Category.POPULAR -> apiHelper.getPopularMovies(page)
            Category.TOP_RATED -> apiHelper.getTopRatedMovies(page)
            Category.UPCOMING -> apiHelper.getUpcomingMovies(page)
            Category.SEARCH -> TODO()
        }.onSuccess {
            /**
             * Returns a resource with success status.
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

    suspend fun getMovieById(movieId: Int): Resource<Movie> {
        apiHelper.getMovieById(movieId).onSuccess {
            return Resource.success(it)
        }.onFailure {
            return Resource.error(it.message ?: "Network issue detected.", null)
        }

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

    suspend fun addMovieToFav(movie: Movie) {
        val movieModel = MovieModel(
            id = movie.id?.toInt(),
            adult = movie.adult,
            backdropPath = movie.backdropPath,
            genreIds = getGenreString(movie),
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            overview = movie.overview,
            popularity = movie.popularity?.toInt(),
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            title = movie.title,
            video = movie.video,
            voteAverage = movie.voteAverage?.toDouble(),
            voteCount = movie.voteCount?.toInt(),
            tagline = movie.tagline
        )

        favouriteMoviesDao.insert(movieModel)
    }

    suspend fun getFavMovieById(id: Int) = favouriteMoviesDao.getFavMovieById(id)

    suspend fun removeFavMovieById(id: Int) = favouriteMoviesDao.deleteFavMovieById(id)

    suspend fun getAllFavMovies(): List<MovieModel>? {
        return favouriteMoviesDao.getAllFavMovies()
    }

    fun getGenreString(movie: Movie): String? {
        if (movie.genres == null) return null

        var genresString = ""

        for (index in movie.genres.indices) {
            genresString += if (index != movie.genres.size - 1)
                movie.genres[index].name + ", "
            else movie.genres[index].name + "."
        }

        println("abcdef: $genresString")
        return genresString
    }

    enum class Category {
        NOW_PLAYING,
        POPULAR,
        TOP_RATED,
        UPCOMING,
        SEARCH
    }
}