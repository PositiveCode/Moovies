package com.geneus.moovies.di.module

import com.geneus.moovies.data.repo.MovieRepo
import org.koin.dsl.module

val repoModule = module {
    single { MovieRepo(get(), get()) }
}