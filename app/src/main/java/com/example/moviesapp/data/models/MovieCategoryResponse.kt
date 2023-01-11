package com.example.moviesapp.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieCategoryResponse(
    val genres: List<Genre>
) {
    @JsonClass(generateAdapter = true)
    data class Genre(
        val id: Int,
        val name: String
    )
}