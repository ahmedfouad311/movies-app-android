package com.example.moviesapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.models.MovieReviewsResponse
import com.example.moviesapp.data.repository.MoviesRepository

class ReviewsViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    private val movieReviewsResult: MutableLiveData<MovieReviewsResponse> by lazy {
        MutableLiveData()
    }

    val movieReviewsLiveData: LiveData<MovieReviewsResponse> = movieReviewsResult

    private val movieReviewsError: MutableLiveData<String> by lazy {
        MutableLiveData()
    }
    val movieReviewsLiveDataError = movieReviewsError

    fun getMovieReviews(movieId: Long) {
        moviesRepository.getReviews(movieId = movieId, page = 1).subscribe({ movieReviewsModel ->
            movieReviewsResult.value = movieReviewsModel
        },
            { errorThrowable -> movieReviewsError.value = errorThrowable.localizedMessage })

    }
}