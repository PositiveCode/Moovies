package com.geneus.moovies.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geneus.moovies.data.repo.MovieRepo
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo: MovieRepo
) : ViewModel() {
    init {

    }

    fun getGenre(){
        viewModelScope.launch {
            repo.getMovieGenre()
        }
    }
}