package com.example.moviesapp.utils

import com.squareup.moshi.Moshi

inline fun <reified T: Any> String.deserialize(): T?{
    return Moshi.Builder().build().adapter(T::class.java).fromJson(this)
}

inline fun <reified T: Any> T.serialize(): String{
    return Moshi.Builder().build().adapter<String>(T::class.java).toJson(this.toString())
}