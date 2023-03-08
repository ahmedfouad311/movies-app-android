package com.example.moviesapp.presentation.Fragments

import PopularSeeAllAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.data.models.PopularResponse
import com.example.moviesapp.presentation.viewModels.MainViewModel
import kotlinx.android.synthetic.main.fragment_popular_see_all.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularSeeAll : Fragment() {
    private lateinit var popularSeeAllAdapter: PopularSeeAllAdapter
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_see_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularSeeAllAdapter = PopularSeeAllAdapter(movieItemCallBack = {
            findNavController().navigate(PopularSeeAllDirections.actionPopularSeeAllToMovieDetails(it))
        })

        viewModel.getPopular()
        viewModel.popularLiveData.observe(viewLifecycleOwner){
            if(it is PopularResponse){
                popularSeeAllAdapter.setData(it.results)
                rvPopularSeeAll.adapter = popularSeeAllAdapter
            }
        }

        rvPopularSeeAll.layoutManager = LinearLayoutManager(activity)
    }
}