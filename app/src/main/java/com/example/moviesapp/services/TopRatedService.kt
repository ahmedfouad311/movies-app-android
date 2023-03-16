package com.example.moviesapp.services

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.data.models.MoviesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TopRatedService {

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,
        @Query("language")
        language: String = "en-US",
        @Query("page")
        page: Int
    ):Single<MoviesResponse>
}