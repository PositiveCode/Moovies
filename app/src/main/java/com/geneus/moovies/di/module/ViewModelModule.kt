package com.geneus.moovies.di.module

import com.geneus.moovies.ui.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
    viewModel { FavouriteViewModel(get()) }
    viewModel { MovieListViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}