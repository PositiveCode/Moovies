package com.geneus.moovies.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geneus.moovies.data.db.model.MovieModel
import com.geneus.moovies.data.repo.MovieRepo
import com.geneus.moovies.utils.Resource
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val repo: MovieRepo
) : ViewModel() {
    private val _movieDetail =
        MutableLiveData<Resource<List<MovieModel>>>()
    val movieDetail: LiveData<Resource<List<MovieModel>>>
        get() = _movieDetail

    fun getAllFavMovies() {
        viewModelScope.launch {
            _movieDetail.postValue(Resource.loading(null))
            _movieDetail.postValue(Resource.success(repo.getAllFavMovies()))
        }
    }

}