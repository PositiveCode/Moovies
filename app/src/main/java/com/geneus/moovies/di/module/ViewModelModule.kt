package com.geneus.moovies.di.module

import com.geneus.moovies.ui.viewmodel.MainViewModel
import com.geneus.moovies.ui.viewmodel.NowPlayingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { NowPlayingViewModel(get()) }
}