package com.geneus.moovies.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geneus.moovies.data.api.model.Movie
import com.geneus.moovies.data.repo.MovieRepo
import com.geneus.moovies.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieDetailsViewModel(
    private val repo: MovieRepo
) : ViewModel() {
    private val _movieDetail =
        MutableLiveData<Resource<Movie>>()
    val movieDetail: LiveData<Resource<Movie>>
        get() = _movieDetail

    fun getMovieById(movieId: Int) {
        viewModelScope.launch {
            _movieDetail.postValue(Resource.loading(null))
            _movieDetail.postValue(repo.getMovieById(movieId))
        }
    }

    fun addMovieToFav(movie: Movie) {
        viewModelScope.launch {
            repo.addMovieToFav(movie)
        }
    }

    fun removeMovieFromFav(movie: Movie) {
        if (movie.id == null) return
        viewModelScope.launch {
            repo.removeFavMovieById(movie.id.toInt())
        }
    }

    fun isMovieAddedFav(movieId: Int?): Boolean = runBlocking {
        if (movieId != null)
            repo.getFavMovieById(movieId) != null
        else false
    }

    fun getGenreString(movie: Movie) = repo.getGenreString(movie)

}