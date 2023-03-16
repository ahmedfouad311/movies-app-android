package com.example.moviesapp.presentation.fragments

import com.example.moviesapp.presentation.adapters.MoviesSeeAllAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.data.filteration.MovieFilter
import com.example.moviesapp.presentation.viewModels.MainViewModel
import kotlinx.android.synthetic.main.fragment_movies_see_all.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesSeeAll : Fragment() {
    private lateinit var moviesSeeAllAdapter: MoviesSeeAllAdapter
    private val viewModel by viewModel<MainViewModel>()
    private val args by navArgs<MoviesSeeAllArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_see_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Type", args.type.name)

        viewModel.getMovies(type = args.type)
        viewModel.topRatedLiveData.observe(viewLifecycleOwner) {
            moviesSeeAllAdapter.setData(it.results)
            rvMoviesSeeAll.adapter = moviesSeeAllAdapter
        }

        rvMoviesSeeAll.layoutManager = LinearLayoutManager(activity)

        viewModel.popularLiveData.observe(viewLifecycleOwner) {
            moviesSeeAllAdapter.setData(it.results)
            rvMoviesSeeAll.adapter = moviesSeeAllAdapter
        }
        rvMoviesSeeAll.layoutManager = LinearLayoutManager(activity)

        viewModel.nowPlayingLiveData.observe(viewLifecycleOwner) {
            moviesSeeAllAdapter.setData(it.results)
            rvMoviesSeeAll.adapter = moviesSeeAllAdapter
        }
        rvMoviesSeeAll.layoutManager = LinearLayoutManager(activity)


        moviesSeeAllAdapter = MoviesSeeAllAdapter(movieItemCallBack = {
            findNavController().navigate(MoviesSeeAllDirections.actionMoviesSeeAllToMovieDetails(it))
        })


        ivMoviesSeeAllBackIcon.setOnClickListener(){
            findNavController().navigate(R.id.mainFragment)
        }
    }
}