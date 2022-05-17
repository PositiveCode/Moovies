package com.geneus.moovies.di.module

import com.geneus.moovies.data.db.AppDatabase
import org.koin.dsl.module

val dbModule = module {
    single { AppDatabase.getInstance(get()) }
    single { get<AppDatabase>().genreDao() }
    single { get<AppDatabase>().favouriteMoviesDao() }
}