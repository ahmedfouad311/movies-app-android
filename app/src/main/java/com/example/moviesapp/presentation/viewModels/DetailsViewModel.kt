package com.example.moviesapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.models.MovieDetailsResponse
import com.example.moviesapp.data.models.MovieTrailersResponse
import com.example.moviesapp.data.repository.MoviesRepository

class DetailsViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val movieDetailsResult: MutableLiveData<MovieDetailsResponse> by lazy {
        MutableLiveData()
    }

    val movieDetailsLiveData: LiveData<MovieDetailsResponse> = movieDetailsResult

    private val movieDetailsError: MutableLiveData<String> by lazy {
        MutableLiveData()
    }
    val movieDetailsLiveDataError = movieDetailsError

    fun getMovieDetails(movieId: Long) {
        moviesRepository.getDetails(movieId = movieId).subscribe({ movieDetailsModel ->
            movieDetailsResult.value = movieDetailsModel
        },
            { errorThrowable -> movieDetailsError.value = errorThrowable.localizedMessage })

    }


    private val movieTrailersResult: MutableLiveData<MovieTrailersResponse> by lazy {
        MutableLiveData()
    }

    val movieTrailersLiveData: LiveData<MovieTrailersResponse> = movieTrailersResult

    private val movieTrailersError: MutableLiveData<String> by lazy {
        MutableLiveData()
    }
    val movieTrailersLiveDataError = movieDetailsError

    fun getMovieTrailers(movieId: Long) {
        moviesRepository.getTrailers(movieId = movieId).subscribe({ movieTrailersModel ->
            movieTrailersResult.value = movieTrailersModel
        },
            { errorThrowable -> movieTrailersError.value = errorThrowable.localizedMessage })

    }
}