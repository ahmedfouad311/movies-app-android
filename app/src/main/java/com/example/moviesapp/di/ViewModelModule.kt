package com.example.moviesapp.di

import com.example.moviesapp.presentation.viewModels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel {
        MainViewModel(moviesRepository = get())
    }
}





