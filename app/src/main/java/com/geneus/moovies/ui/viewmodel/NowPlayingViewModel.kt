package com.geneus.moovies.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geneus.moovies.data.db.model.ApiSource
import com.geneus.moovies.data.repo.MovieRepo
import kotlinx.coroutines.launch

class NowPlayingViewModel(
    private val repo: MovieRepo
) : ViewModel() {

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            repo.getMoviesBySource(ApiSource.NOW_PLAYING)
        }
    }
}