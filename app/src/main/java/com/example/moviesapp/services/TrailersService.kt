package com.example.moviesapp.services

import com.example.moviesapp.data.models.MovieTrailersResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrailersService {
    @GET("movie/{movie_id}/videos")
    fun getMovieTrailers(
        @Path("movie_id")
        movie_id: Long,
        @Query("api_key")
        apiKey: String,
        @Query("language")
        language: String = "en-US"
    ): Single<MovieTrailersResponse>
}