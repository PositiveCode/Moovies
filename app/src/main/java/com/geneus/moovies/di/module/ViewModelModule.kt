package com.geneus.moovies.di.module

import com.geneus.moovies.ui.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { NowPlayingViewModel(get()) }
    viewModel { PopularViewModel(get()) }
    viewModel { TopRatedViewModel(get()) }
    viewModel { UpcomingViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
    viewModel { FavouriteViewModel(get()) }
}