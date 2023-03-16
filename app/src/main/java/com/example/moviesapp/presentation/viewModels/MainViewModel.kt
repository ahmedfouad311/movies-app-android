package com.example.moviesapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.filteration.MovieFilter
import com.example.moviesapp.data.models.MovieCategoryResponse
import com.example.moviesapp.data.models.MoviesResponse
import com.example.moviesapp.data.repository.MoviesRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class MainViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    private val compositeDisposable by lazy {
        CompositeDisposable()
    }


    private val nowPlayingResult: MutableLiveData<MoviesResponse> by lazy {
        MutableLiveData()
    }

    val nowPlayingLiveData: LiveData<MoviesResponse> = nowPlayingResult

    private val nowPlayingError: MutableLiveData<String> by lazy {
        MutableLiveData()
    }
    val nowPlayingLiveDataError: LiveData<MoviesResponse> by lazy {
        MutableLiveData()
    }

    fun getNowPlaying() {
        moviesRepository.getNowPlayingFilms(page = 1).subscribe({ nowPlayingModel ->
            nowPlayingResult.value = nowPlayingModel
        },
            { errorThrowable -> nowPlayingError.value = errorThrowable.localizedMessage }).addTo(compositeDisposable)


    }

    private val popularResult: MutableLiveData<MoviesResponse> by lazy {
        MutableLiveData()
    }

    val popularLiveData: LiveData<MoviesResponse> = popularResult

    private val popularError: MutableLiveData<String> by lazy {
        MutableLiveData()
    }
    val popularLiveDataError: LiveData<String> get() = popularError

    fun getPopular() {
        moviesRepository.getPopularFilms(page = 1).subscribe({ popularModel ->
            popularResult.postValue(popularModel)
        },
            { errorThrowable -> popularError.value = errorThrowable.localizedMessage }).addTo(compositeDisposable)


    }

    private val topRatedResult: MutableLiveData<MoviesResponse> by lazy {
        MutableLiveData()
    }

    val topRatedLiveData: LiveData<MoviesResponse> = topRatedResult

    private val topRatedError: MutableLiveData<String> by lazy {
        MutableLiveData()
    }
    val topRatedLiveDataError: LiveData<MoviesResponse> by lazy {
        MutableLiveData()
    }

    fun getTopRated() {
        moviesRepository.getTopRatedFilms(page = 1).subscribe({ topRatedModel ->
            topRatedResult.value = topRatedModel
        },
            { errorThrowable -> topRatedError.value = errorThrowable.localizedMessage }).addTo(compositeDisposable)


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
            { errorThrowable -> categoryError.value = errorThrowable.localizedMessage }).addTo(compositeDisposable)


    }

    fun getMovies(type: MovieFilter) {
        when (type) {
            MovieFilter.POPULAR -> getPopular()
            MovieFilter.TOP_RATED -> getTopRated()
            MovieFilter.NOW_PLAYING -> getNowPlaying()
        }
    }



    private fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(this)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}