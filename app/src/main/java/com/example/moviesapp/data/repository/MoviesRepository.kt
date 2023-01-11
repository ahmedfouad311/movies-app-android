package com.example.moviesapp.data.repository

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.data.models.MovieCategoryResponse
import com.example.moviesapp.data.models.PlayingNowResponse
import com.example.moviesapp.data.models.PopularResponse
import com.example.moviesapp.data.models.TopRatedResponse
import com.example.moviesapp.services.CategoryService
import com.example.moviesapp.services.PlayingNowService
import com.example.moviesapp.services.PopularService
import com.example.moviesapp.services.TopRatedService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class MoviesRepository(
    private val nowPlayingService: PlayingNowService,
    private val popularService: PopularService,
    private val topRatedService: TopRatedService,
    private val categoryService: CategoryService,
    private val ioThread: Scheduler,
    private val mainThread: Scheduler
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
        page: Int
    ): Single<MovieCategoryResponse> = categoryService.getMoviesCategory(apiKey)
        .subscribeOn(ioThread).observeOn(mainThread)
}


