package com.geneus.moovies.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geneus.moovies.data.api.model.Movie
import com.geneus.moovies.data.repo.MovieRepo
import com.geneus.moovies.utils.Resource
import kotlinx.coroutines.launch

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
}