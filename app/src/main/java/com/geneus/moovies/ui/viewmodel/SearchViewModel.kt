package com.geneus.moovies.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geneus.moovies.data.api.model.Movie
import com.geneus.moovies.data.db.model.Genre
import com.geneus.moovies.data.repo.MovieRepo
import com.geneus.moovies.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SearchViewModel(
    private val repo: MovieRepo
) : ViewModel() {

    private val _movieList =
        MutableLiveData<Resource<ArrayList<Movie>>>()
    val movieList: LiveData<Resource<ArrayList<Movie>>>
        get() = _movieList
    private var page = 1

    fun getSearchedMovie(query: String) {
        viewModelScope.launch {
            _movieList.postValue(Resource.loading(null))
            _movieList.postValue(repo.getMoviesByQuery(query, page++))
        }
    }

    fun getLocalGenreList(): List<Genre>? = runBlocking {
        repo.getMovieGenresLocally()
    }
}