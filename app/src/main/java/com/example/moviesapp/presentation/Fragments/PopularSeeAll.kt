package com.example.moviesapp.presentation.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.presentation.Adapters.PopularSeeAllAdapter
import kotlinx.android.synthetic.main.fragment_popular_see_all.*

class PopularSeeAll : Fragment() {
    private lateinit var popularSeeAllAdapter: PopularSeeAllAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_see_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularSeeAllAdapter = PopularSeeAllAdapter()

        rvPopularSeeAll.layoutManager = LinearLayoutManager(activity)
        rvPopularSeeAll.adapter = popularSeeAllAdapter
    }
}