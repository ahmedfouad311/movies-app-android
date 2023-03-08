package com.example.moviesapp.data.repository

import android.content.SharedPreferences
import android.os.Bundle
import androidx.navigation.NavArgs
import com.example.moviesapp.BuildConfig
import com.example.moviesapp.data.models.*
import com.example.moviesapp.presentation.Fragments.MovieDetailsArgs
import com.example.moviesapp.services.*
import com.example.moviesapp.utils.deserialize
import com.example.moviesapp.utils.serialize
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single


class MoviesRepository(
    private val nowPlayingService: PlayingNowService,
    private val popularService: PopularService,
    private val topRatedService: TopRatedService,
    private val categoryService: CategoryService,
    private val detailsService: DetailsService,
    private val trailersService: TrailersService,
    private val reviewsService: ReviewsService,
    private val castService: CastService,
    private val ioThread: Scheduler,
    private val mainThread: Scheduler,
    private val sharedPreferences: SharedPreferences
) {


    fun getNowPlayingFilms(
        apiKey: String = BuildConfig.API_KEY,
        page: Int
    ): Single<PlayingNowResponse> = nowPlayingService.getPlayingNow(apiKey, page = page)
        .subscribeOn(ioThread).observeOn(mainThread)

    fun getPopularFilms(
        apiKey: String = BuildConfig.API_KEY,
        page: Int
    ): Single<PopularResponse> = popularService.getPopular(apiKey, page = page)
        .subscribeOn(ioThread).observeOn(mainThread)

    fun getTopRatedFilms(
        apiKey: String = BuildConfig.API_KEY,
        page: Int
    ): Single<TopRatedResponse> = topRatedService.getTopRated(apiKey, page = page)
        .subscribeOn(ioThread).observeOn(mainThread)

    fun getCategory(
        apiKey: String = BuildConfig.API_KEY,
    ): Single<MovieCategoryResponse> = categoryService.getMoviesCategory(apiKey)
        .subscribeOn(ioThread).observeOn(mainThread)
        .doOnSuccess {
//            val sharedPreferences: SharedPreferences = application.getSharedPreferences("MOVIES_APP_SHARED_PREF", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply{
                putString("MOVIE_APP", it.genres.serialize())
            }
            val deserializedData = sharedPreferences.getString("MOVIE_APP", String().deserialize())
        }

    fun getDetails(
        apiKey: String = BuildConfig.API_KEY,
        movieId: Long
    ): Single<MovieDetailsResponse> = detailsService.getMovieDetails(movie_id = movieId, apiKey = apiKey)
        .subscribeOn(ioThread).observeOn(mainThread)


    fun getTrailers(
        apiKey: String = BuildConfig.API_KEY,
        movieId: Long
    ): Single<MovieTrailersResponse> = trailersService.getMovieTrailers(movie_id = movieId, apiKey = apiKey)
        .subscribeOn(ioThread).observeOn(mainThread)

    fun getReviews(
        apiKey: String = BuildConfig.API_KEY,
        movieId: Long,
        page: Int
    ): Single<MovieReviewsResponse> = reviewsService.getMovieReviews(movie_id = movieId, apiKey = apiKey, page = page)
        .subscribeOn(ioThread).observeOn(mainThread)

    fun getCast(
        apiKey: String = BuildConfig.API_KEY,
        movieId: Long,
    ): Single<MovieCastResponse> = castService.getMovieCast(movie_id = movieId, apiKey = apiKey)
}


