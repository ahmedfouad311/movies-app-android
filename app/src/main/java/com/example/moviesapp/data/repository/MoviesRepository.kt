package com.example.moviesapp.data.repository

import android.content.SharedPreferences
import com.example.moviesapp.BuildConfig
import com.example.moviesapp.Transformer
import com.example.moviesapp.data.models.*
import com.example.moviesapp.services.*
import com.example.moviesapp.utils.deserialize
import com.example.moviesapp.utils.serialize
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

    private fun <T : Any> getTransformer(): Transformer<T> = Transformer()


    fun getNowPlayingFilms(
        page: Int
    ): Single<MoviesResponse> = nowPlayingService.getPlayingNow(page = page)
        .compose(getTransformer())

    fun getPopularFilms(
        page: Int
    ): Single<MoviesResponse> = popularService.getPopular(page = page)
        .compose(getTransformer())

    fun getTopRatedFilms(
        page: Int
    ): Single<MoviesResponse> = topRatedService.getTopRated(page = page)
        .compose(getTransformer())

    fun getCategory(
    ): Single<MovieCategoryResponse> = categoryService.getMoviesCategory()
        .compose(getTransformer())
        .doOnSuccess {
//            val sharedPreferences: SharedPreferences = application.getSharedPreferences("MOVIES_APP_SHARED_PREF", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply{
                putString("MOVIE_APP", it.genres.serialize())
            }
            val deserializedData = sharedPreferences.getString("MOVIE_APP", String().deserialize())
        }

    fun getDetails(
        movieId: Long
    ): Single<MovieDetailsResponse> = detailsService.getMovieDetails(movie_id = movieId)
        .compose(getTransformer())


    fun getTrailers(
        movieId: Long
    ): Single<MovieTrailersResponse> = trailersService.getMovieTrailers(movie_id = movieId)
        .compose(getTransformer())

    fun getReviews(
        movieId: Long,
        page: Int
    ): Single<MovieReviewsResponse> = reviewsService.getMovieReviews(movie_id = movieId, page = page)
        .compose(getTransformer())

    fun getCast(
        movieId: Long,
    ): Single<MovieCastResponse> = castService.getMovieCast(movie_id = movieId)
        .compose(getTransformer())
}


