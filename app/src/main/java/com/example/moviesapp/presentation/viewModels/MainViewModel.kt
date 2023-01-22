package com.example.moviesapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.models.MovieCategoryResponse
import com.example.moviesapp.data.models.PlayingNowResponse
import com.example.moviesapp.data.models.PopularResponse
import com.example.moviesapp.data.models.TopRatedResponse
import com.example.moviesapp.data.repository.MoviesRepository
import com.example.moviesapp.network.ApplicationNetwork
import com.example.moviesapp.services.PlayingNowService
import com.example.moviesapp.services.PopularService
import com.example.moviesapp.services.TopRatedService

class MainViewModel(private val moviesRepository: MoviesRepository): ViewModel() {

    private val nowPlayingResult: MutableLiveData<PlayingNowResponse> by lazy {
        MutableLiveData()
    }

    val nowPlayingLiveData: LiveData<PlayingNowResponse> = nowPlayingResult

    private val nowPlayingError: MutableLiveData<String> by lazy {
        MutableLiveData()
    }
    val nowPlayingLiveDataError: LiveData<PlayingNowResponse> by lazy {
        MutableLiveData()
    }

    fun getNowPlaying() {
        moviesRepository.getNowPlayingFilms(page = 1).subscribe({ nowPlayingModel ->
            nowPlayingResult.value = nowPlayingModel
        },
            { errorThrowable -> nowPlayingError.value = errorThrowable.localizedMessage })


    }

    private val popularResult: MutableLiveData<PopularResponse> by lazy {
        MutableLiveData()
    }

    val popularLiveData: LiveData<PopularResponse> = popularResult

    private val popularError: MutableLiveData<String> by lazy {
        MutableLiveData()
    }
    val popularLiveDataError: LiveData<String> get() = popularError

    fun getPopular() {
        moviesRepository.getPopularFilms(page = 1).subscribe({ popularModel ->
            popularResult.postValue(popularModel)
        },
            { errorThrowable -> popularError.value = errorThrowable.localizedMessage })


    }

    private val topRatedResult: MutableLiveData<TopRatedResponse> by lazy {
        MutableLiveData()
    }

    val topRatedLiveData: LiveData<TopRatedResponse> = topRatedResult

    private val topRatedError: MutableLiveData<String> by lazy {
        MutableLiveData()
    }
    val topRatedLiveDataError: LiveData<TopRatedResponse> by lazy {
        MutableLiveData()
    }

    fun getTopRated() {
        moviesRepository.getTopRatedFilms(page = 1).subscribe({ topRatedModel ->
            topRatedResult.value = topRatedModel
        },
            { errorThrowable -> topRatedError.value = errorThrowable.localizedMessage })


    }

    private val categoryResult: MutableLiveData<MovieCategoryResponse> by lazy {
        MutableLiveData()
    }

    val categoryLiveData: LiveData<MovieCategoryResponse> = categoryResult

    private val categoryError: MutableLiveData<String> by lazy {
        MutableLiveData()
    }
    val categoryErrorLiveData: LiveData<MovieCategoryResponse> by lazy {
        MutableLiveData()
    }

    fun getCategories() {
        moviesRepository.getCategory().subscribe({ categoryModel ->
            categoryResult.value = categoryModel
        },
            { errorThrowable -> categoryError.value = errorThrowable.localizedMessage })


    }
}