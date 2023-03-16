package com.example.moviesapp.services

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.data.models.MovieDetailsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsService {
    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id")
        movie_id: Long,
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,
        @Query("language")
        language: String = "en-US"
    ): Single<MovieDetailsResponse>
}