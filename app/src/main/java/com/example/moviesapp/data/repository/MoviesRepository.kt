package com.example.moviesapp.data.repository

import android.content.SharedPreferences
import android.os.Bundle
import androidx.core.content.edit
import androidx.navigation.NavArgs
import com.example.moviesapp.BuildConfig
import com.example.moviesapp.IoTransformers
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
    private val sharedPreferences: SharedPreferences
) {

    private fun <T : Any> getIOTransformer(): IoTransformers<T> = IoTransformers()

    fun getNowPlayingFilms(
        apiKey: String = BuildConfig.API_KEY,
        page: Int
    ): Single<PlayingNowResponse> = nowPlayingService.getPlayingNow(apiKey, page = page)
        .compose(getIOTransformer())

    fun getPopularFilms(
        apiKey: String = BuildConfig.API_KEY,
        page: Int
    ): Single<PopularResponse> = popularService.getPopular(apiKey, page = page)
        .compose(getIOTransformer())

    fun getTopRatedFilms(
        apiKey: String = BuildConfig.API_KEY,
        page: Int
    ): Single<TopRatedResponse> = topRatedService.getTopRated(apiKey, page = page)
        .compose(getIOTransformer())

    fun getCategory(
        apiKey: String = BuildConfig.API_KEY,
    ): Single<MovieCategoryResponse> = categoryService.getMoviesCategory(apiKey)
        .compose(getIOTransformer())
        .doOnSuccess {
            sharedPreferences.edit {
                putString("MOVIE_APP", it.genres.serialize())
            }

            sharedPreferences.getString("MOVIE_APP", "")?.deserialize<List<MovieCategoryResponse.Genre>>()
        }

    fun getDetails(
        apiKey: String = BuildConfig.API_KEY,
        movieId: Long
    ): Single<MovieDetailsResponse> =
        detailsService.getMovieDetails(movie_id = movieId, apiKey = apiKey)
            .compose(getIOTransformer())


    fun getTrailers(
        apiKey: String = BuildConfig.API_KEY,
        movieId: Long
    ): Single<MovieTrailersResponse> =
        trailersService.getMovieTrailers(movie_id = movieId, apiKey = apiKey)
            .compose(getIOTransformer())

    fun getReviews(
        apiKey: String = BuildConfig.API_KEY,
        movieId: Long,
        page: Int
    ): Single<MovieReviewsResponse> =
        reviewsService.getMovieReviews(movie_id = movieId, apiKey = apiKey, page = page)
            .compose(getIOTransformer())

    fun getCast(
        apiKey: String = BuildConfig.API_KEY,
        movieId: Long,
    ): Single<MovieCastResponse> = castService.getMovieCast(movie_id = movieId, apiKey = apiKey)
}


