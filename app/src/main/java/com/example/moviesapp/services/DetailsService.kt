package com.example.moviesapp.services

import com.example.moviesapp.data.models.MovieDetailsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailsService {
    @GET("movie/")
    fun getMoviesCategory(
        @Query("api_key")
        apiKey: String,
        @Query("language")
        language: String = "en-US",
        @Query("movie_id")
        movie_id: Int
    ): Single<MovieDetailsResponse>
}