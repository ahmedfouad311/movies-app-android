package com.example.moviesapp.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieCastResponse(
    val cast: List<Cast>?,
    val crew: List<Crew>,
    val id: Int?
) {

    @JsonClass(generateAdapter = true)
    data class Cast(
        val adult: Boolean,
        val cast_id: Int,
        val character: String,
        val credit_id: String,
        val gender: Int,
        val id: Int,
        val known_for_department: String,
        val name: String,
        val order: Int,
        val original_name: String,
        val popularity: Double,
        val profile_path: String?
    )

    @JsonClass(generateAdapter = true)
    data class Crew(
        val adult: Boolean,
        val credit_id: String,
        val department: String,
        val gender: Int,
        val id: Int,
        val job: String,
        val known_for_department: String,
        val name: String,
        val original_name: String,
        val popularity: Double,
        val profile_path: String?
    )
}