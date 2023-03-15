package com.example.moviesapp.presentation.Fragments

import TopRatedSeeAllAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.data.models.TopRatedResponse
import com.example.moviesapp.presentation.viewModels.MainViewModel
import kotlinx.android.synthetic.main.fragment_top_rated_see_all.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopRatedSeeAll : Fragment(R.layout.fragment_top_rated_see_all) {

    private val args by navArgs<TopRatedSeeAllArgs>()

    private val topRatedSeeAllAdapter: TopRatedSeeAllAdapter by lazy {
        TopRatedSeeAllAdapter(itemCallback)
    }

    private val itemCallback: (movieId: Long) -> Unit = {
        findNavController().navigate(TopRatedSeeAllDirections.actionTopRatedSeeAllToMovieDetails(it))
    }

    private val viewModel by viewModel<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData(type = args.type)
        viewModel.topRatedLiveData.observe(viewLifecycleOwner) {
            topRatedSeeAllAdapter.setData(it.results)
            rvTopRatedSeeAll.adapter = topRatedSeeAllAdapter
        }

        viewModel.popularLiveData.observe(viewLifecycleOwner) {
            topRatedSeeAllAdapter.setData(it.results)
            rvTopRatedSeeAll.adapter = topRatedSeeAllAdapter
        }

        viewModel.nowPlayingLiveData.observe(viewLifecycleOwner) {
            topRatedSeeAllAdapter.setData(it.results)
            rvTopRatedSeeAll.adapter = topRatedSeeAllAdapter
        }
    }
}