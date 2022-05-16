package com.geneus.moovies.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.geneus.moovies.data.repo.MovieRepo

class MovieDetailsViewModel(
    private val repo: MovieRepo
) : ViewModel() {

}