package com.geneus.moovies.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geneus.moovies.data.api.ApiService
import com.geneus.moovies.utils.NetworkHelper
import kotlinx.coroutines.launch

class MainViewModel(
    private val apiService: ApiService,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    fun getMovieGenre() {
        viewModelScope.launch {
            apiService.getMovieGenre().onSuccess {
                println("Success")
            }.onFailure {
                println("getNowPlayingMovies failed: ${it.message}")
            }
        }
    }
}