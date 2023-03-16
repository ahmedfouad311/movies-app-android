package com.example.moviesapp.services

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.data.models.MoviesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayingNowService {

    @GET("movie/now_playing")
    fun getPlayingNow(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,
        @Query("language")
        language: String = "en-US",
        @Query("page")
        page: Int
    ): Single<MoviesResponse>
}