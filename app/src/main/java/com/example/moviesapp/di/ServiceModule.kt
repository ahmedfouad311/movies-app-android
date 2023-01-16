package com.example.moviesapp.di

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.services.CategoryService
import com.example.moviesapp.services.PlayingNowService
import com.example.moviesapp.services.PopularService
import com.example.moviesapp.services.TopRatedService
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create


val serviceModule = module {
    single {
        getRetrofitBuilder()
    }

    factory {
        getRetrofitBuilder().create(PlayingNowService::class.java)
    }

    factory {
        getRetrofitBuilder().create(PopularService::class.java)
    }

    factory {
        getRetrofitBuilder().create(TopRatedService::class.java)
    }

    factory {
        getRetrofitBuilder().create(CategoryService::class.java)
    }
}

fun getRetrofitBuilder(): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        )
        .build()
}