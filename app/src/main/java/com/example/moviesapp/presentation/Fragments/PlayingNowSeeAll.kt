package com.example.moviesapp.presentation.Fragments

import PlayingNowSeeAllAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.data.models.PlayingNowResponse
import com.example.moviesapp.presentation.viewModels.MainViewModel
import kotlinx.android.synthetic.main.fragment_playing_now_see_all.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayingNowSeeAll : Fragment() {
    private lateinit var playingNowSeeAllAdapter: PlayingNowSeeAllAdapter
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playing_now_see_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playingNowSeeAllAdapter = PlayingNowSeeAllAdapter()

        viewModel.getNowPlaying()
        viewModel.nowPlayingLiveData.observe(viewLifecycleOwner){
            if(it is PlayingNowResponse){
                playingNowSeeAllAdapter.setData(it.results)
                rvPlayingNowSeeAll.adapter = playingNowSeeAllAdapter
            }
        }

        rvPlayingNowSeeAll.layoutManager = LinearLayoutManager(activity)
    }

}