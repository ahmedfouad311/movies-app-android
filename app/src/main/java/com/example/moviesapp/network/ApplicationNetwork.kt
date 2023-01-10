package com.example.moviesapp.network

import com.example.moviesapp.BuildConfig
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApplicationNetwork private constructor(private val retrofit: Retrofit){

    companion object{
        private lateinit var retrofit: Retrofit

        private lateinit var instance: ApplicationNetwork

        fun newInstance(baseUrl: String = BuildConfig.API_BASE_URL): ApplicationNetwork{
            if (this::retrofit.isInitialized.not())
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
            if (this::instance.isInitialized.not())
                instance = ApplicationNetwork(this.retrofit)
            return instance
        }
    }

    fun <T : Any> createService(clazz: Class<T>): T = retrofit.create(clazz)
}