package com.example.moviesapp.presentation.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.presentation.Adapters.TopRatedSeeAllAdapter
import kotlinx.android.synthetic.main.fragment_top_rated_see_all.*

class TopRatedSeeAll : Fragment() {
    private lateinit var topRatedSeeAllAdapter: TopRatedSeeAllAdapter
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

        rvTopRatedSeeAll.layoutManager = LinearLayoutManager(activity)
        rvTopRatedSeeAll.adapter = topRatedSeeAllAdapter
    }
}