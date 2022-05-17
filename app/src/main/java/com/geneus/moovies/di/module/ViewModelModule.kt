package com.geneus.moovies.di.module

import com.geneus.moovies.ui.viewmodel.FavouriteViewModel
import com.geneus.moovies.ui.viewmodel.MainViewModel
import com.geneus.moovies.ui.viewmodel.MovieDetailsViewModel
import com.geneus.moovies.ui.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
    viewModel { FavouriteViewModel(get()) }
    viewModel { MovieListViewModel(get()) }
}