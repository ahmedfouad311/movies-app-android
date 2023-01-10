package com.example.moviesapp.di

import com.example.moviesapp.data.repository.MoviesRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val moviesRepositoryModule = module {
    factory {
        MoviesRepository(
            nowPlayingService = get(),
            popularService = get(),
            topRatedService = get(),
            ioThread = get(named("io")),
            mainThread = get(named("main"))
        )
    }
}