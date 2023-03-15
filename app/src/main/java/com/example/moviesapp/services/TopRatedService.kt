package com.example.moviesapp.services

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.data.models.TopRatedResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale

interface TopRatedService {

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,
        @Query("language")
        language: String = Locale.getDefault().language,
        @Query("page")
        page: Int
    ):Single<TopRatedResponse>
}