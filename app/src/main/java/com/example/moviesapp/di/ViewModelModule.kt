package com.example.moviesapp.di

import com.example.moviesapp.presentation.viewModels.DetailsViewModel
import com.example.moviesapp.presentation.viewModels.MainViewModel
import com.example.moviesapp.presentation.viewModels.ReviewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel {
        MainViewModel(moviesRepository = get())
    }

    viewModel{
        DetailsViewModel(moviesRepository = get())
    }

    viewModel{
        ReviewsViewModel(moviesRepository = get())
    }
}





