package com.geneus.moovies.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geneus.moovies.data.api.model.Movie
import com.geneus.moovies.data.repo.MovieRepo
import com.geneus.moovies.utils.Resource
import kotlinx.coroutines.launch

class NowPlayingViewModel(
    private val repo: MovieRepo
) : ViewModel() {

    private val _movieList =
        MutableLiveData<Resource<List<Movie>>>()
    val movieList: LiveData<Resource<List<Movie>>>
        get() = _movieList

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            _movieList.postValue(Resource.loading(null))
            _movieList.postValue(repo.getMoviesByCategory(MovieRepo.Category.NOW_PLAYING))
        }
    }
}