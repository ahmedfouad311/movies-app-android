package com.example.moviesapp.services

import com.example.moviesapp.data.models.PopularResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularService {

    @GET("movie/popular")
    fun getPopular(
        @Query("api_key")
        apiKey: String,
        @Query("language")
        language: String = "en-US",
        @Query("page")
        page: Int
    ): Single<PopularResponse>
}