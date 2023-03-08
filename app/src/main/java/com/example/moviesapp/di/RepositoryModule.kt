package com.example.moviesapp.di

import android.content.Context
import androidx.navigation.navArgument
import com.example.moviesapp.data.repository.MoviesRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val moviesRepositoryModule = module {
    factory {
        MoviesRepository(
            nowPlayingService = get(),
            popularService = get(),
            topRatedService = get(),
            categoryService = get(),
            detailsService = get(),
            trailersService = get(),
            reviewsService = get(),
            castService = get(),
            ioThread = get(named("io")),
            mainThread = get(named("main")),
            sharedPreferences = androidApplication().getSharedPreferences("MOVIES_APP_SHARED_PREF", Context.MODE_PRIVATE)
        )
    }
}