package com.geneus.moovies.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geneus.moovies.data.repo.MovieRepo
import kotlinx.coroutines.launch

class TopRatedViewModel(
    private val repo: MovieRepo
) : ViewModel() {

    fun getTopRatedMovies() {
        viewModelScope.launch {
            repo.getMoviesBySource(MovieRepo.Category.TOP_RATED)
        }
    }
}