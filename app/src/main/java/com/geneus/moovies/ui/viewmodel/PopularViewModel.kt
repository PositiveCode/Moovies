package com.geneus.moovies.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geneus.moovies.data.repo.MovieRepo
import kotlinx.coroutines.launch

class PopularViewModel(
    private val repo: MovieRepo
) : ViewModel() {

    fun getPopularMovies() {
        viewModelScope.launch {
            repo.getMoviesBySource(MovieRepo.Category.POPULAR)
        }
    }
}