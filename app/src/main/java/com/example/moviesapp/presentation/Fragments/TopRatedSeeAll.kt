package com.example.moviesapp.presentation.Fragments

import TopRatedSeeAllAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.data.models.TopRatedResponse
import com.example.moviesapp.presentation.viewModels.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_top_rated_see_all.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopRatedSeeAll : Fragment() {
    private lateinit var topRatedSeeAllAdapter: TopRatedSeeAllAdapter
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated_see_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topRatedSeeAllAdapter = TopRatedSeeAllAdapter()

        viewModel.getTopRated()
        viewModel.result3.observe(viewLifecycleOwner){
            if(it is TopRatedResponse){
                topRatedSeeAllAdapter.setData(it.results)
                rvTopRatedSeeAll.adapter = topRatedSeeAllAdapter
            }
        }

        rvTopRatedSeeAll.layoutManager = LinearLayoutManager(activity)
    }
}