package com.example.moviesapp.app

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import com.example.moviesapp.di.moviesRepositoryModule
import com.example.moviesapp.di.schedulerModule
import com.example.moviesapp.di.serviceModule
import com.example.moviesapp.di.viewModelModules

class Application: Application() {
    override fun onCreate() {
        super.onCreate()

        val imageLoader = ImageLoader.Builder(this).componentRegistry {
            add(SvgDecoder(this@Application))
        }.build()

        Coil.setImageLoader(imageLoader)

        startKoin {
            androidContext(this@Application)
            modules(
                listOf(
                    //here I should mention the app modules
                    viewModelModules,
                    moviesRepositoryModule,
                    serviceModule,
                    schedulerModule
                )
            )
        }
    }
}