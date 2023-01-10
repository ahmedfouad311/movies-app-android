package com.example.moviesapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.models.PlayingNowResponse
import com.example.moviesapp.data.models.PopularResponse
import com.example.moviesapp.data.models.TopRatedResponse
import com.example.moviesapp.data.repository.MoviesRepository
import com.example.moviesapp.network.ApplicationNetwork
import com.example.moviesapp.services.PlayingNowService
import com.example.moviesapp.services.PopularService
import com.example.moviesapp.services.TopRatedService

class MainViewModel(private val moviesRepository: MoviesRepository): ViewModel() {


//    init {
//        val playingNowApi: PlayingNowService = ApplicationNetwork.newInstance().createService(PlayingNowService::class.java)
//        val popularApi: PopularService = ApplicationNetwork.newInstance().createService(PopularService::class.java)
//        val topRatedApi: TopRatedService = ApplicationNetwork.newInstance().createService(TopRatedService::class.java)
//        appRepo = MoviesRepository(nowPlayingService = playingNowApi, popularService = popularApi, topRatedService = topRatedApi)
//    }

    private val m_result: MutableLiveData<PlayingNowResponse> by lazy {
        MutableLiveData()
    }

    val result: LiveData<PlayingNowResponse> = m_result

    private val m_error: MutableLiveData<String> by lazy {
        MutableLiveData()
    }
    val error: LiveData<PlayingNowResponse> by lazy {
        MutableLiveData()
    }

    fun getNowPlaying() {
        moviesRepository.getNowPlayingFilms(page = 1).subscribe({ nowPlayingModel ->
            m_result.value = nowPlayingModel
        },
            { errorThrowable -> m_error.value = errorThrowable.localizedMessage })


    }

    private val m_result2: MutableLiveData<PopularResponse> by lazy {
        MutableLiveData()
    }

    val result2: LiveData<PopularResponse> = m_result2

    private val m_error2: MutableLiveData<String> by lazy {
        MutableLiveData()
    }
    val error2: LiveData<PopularResponse> by lazy {
        MutableLiveData()
    }

    fun getPopular() {
        moviesRepository.getPopularFilms(page = 1).subscribe({ popularModel ->
            m_result2.value = popularModel
        },
            { errorThrowable -> m_error2.value = errorThrowable.localizedMessage })


    }

    private val m_result3: MutableLiveData<TopRatedResponse> by lazy {
        MutableLiveData()
    }

    val result3: LiveData<TopRatedResponse> = m_result3

    private val m_error3: MutableLiveData<String> by lazy {
        MutableLiveData()
    }
    val error3: LiveData<TopRatedResponse> by lazy {
        MutableLiveData()
    }

    fun getTopRated() {
        moviesRepository.getTopRatedFilms(page = 1).subscribe({ topRatedModel ->
            m_result3.value = topRatedModel
        },
            { errorThrowable -> m_error3.value = errorThrowable.localizedMessage })


    }
}