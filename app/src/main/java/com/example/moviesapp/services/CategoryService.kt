package com.example.moviesapp.services

import com.example.moviesapp.data.models.MovieCategoryResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryService {
    @GET("genre/movie/list")
    fun getMoviesCategory(
        @Query("api_key")
        apiKey: String,
        @Query("language")
        language: String = "en-US"
    ): Single<MovieCategoryResponse>
}