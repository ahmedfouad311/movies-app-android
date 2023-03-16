package com.example.moviesapp.presentation.fragments

import PlayingNowAdapter
import PopularAdapter
import TopRatedAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.moviesapp.R
import com.example.moviesapp.data.filteration.MovieFilter
import com.example.moviesapp.data.models.MovieCategoryResponse
import com.example.moviesapp.data.models.MoviesResponse
import com.example.moviesapp.presentation.viewModels.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {
    private lateinit var playingNowAdapter: PlayingNowAdapter
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var topRatedAdapter: TopRatedAdapter
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Playing Now Recycler View
        playingNowAdapter = PlayingNowAdapter(movieItemCallBack = {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToMovieDetails(it))
        })

        viewModel.getNowPlaying()
        viewModel.nowPlayingLiveData.observe(viewLifecycleOwner) {
            if(it is MoviesResponse){
                Log.d(MainFragment::class.java.simpleName, "Playing Now: ${it.results}")
                playingNowAdapter.setData(it.results)
                rvPlayingNow.adapter = playingNowAdapter
            }
        }

        rvPlayingNow.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        var snapHelperPlayingNow: SnapHelper = LinearSnapHelper()
        snapHelperPlayingNow.attachToRecyclerView(rvPlayingNow)


        // Popular Recycler View
        popularAdapter = PopularAdapter(movieItemCallBack = {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToMovieDetails(it))
        })
        viewModel.getPopular()
        viewModel.popularLiveData.observe(viewLifecycleOwner){
            if(it is MoviesResponse){
                Log.d(MainFragment::class.java.simpleName, "Popular: ${it.results}")
                popularAdapter.setData(it.results)
                rvPopular.adapter = popularAdapter
            }
        }

        viewModel.popularLiveDataError.observe(viewLifecycleOwner){
            Log.d("E", "Error2" + it)
        }
        rvPopular.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)




        // Top Rated Recycler View
        topRatedAdapter = TopRatedAdapter(movieItemCallBack = {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToMovieDetails(it))
        })
        viewModel.getTopRated()
        viewModel.topRatedLiveData.observe(viewLifecycleOwner){
            if(it is MoviesResponse){
                Log.d(MainFragment::class.java.simpleName, "Top Rated: ${it.results}")
                topRatedAdapter.setData(it.results)
                rvTopRated.adapter = topRatedAdapter
            }
        }

        rvTopRated.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)


        viewModel.getCategories()
        viewModel.categoryLiveData.observe(viewLifecycleOwner){
            if(it is MovieCategoryResponse){
                Log.d(MainFragment::class.java.simpleName, "Categories: ${it.genres}")
//                val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("MOVIES_APP_SHARED_PREF", Context.MODE_PRIVATE)
//                val editor = sharedPreferences.edit()
//                Log.d(MainFragment::class.java.simpleName, "First Category  " + it.genres[0].id.toString() + " " + it.genres[0].name)
//                editor.apply{
//                    for(i in it.genres){
//                        putString(i.id.toString(), i.name)
//                    }
//                }.apply()
//                Log.d(MainFragment::class.java.simpleName, "Shared Pref" + sharedPreferences.all)

            }
        }




        // See All Button Playing Now
        tvPlayingNowSeeAll.setOnClickListener(View.OnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToMoviesSeeAll(MovieFilter.NOW_PLAYING))
        })


        // See All Button Popular Recycler View
        tvPopularSeeAll.setOnClickListener(View.OnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToMoviesSeeAll(MovieFilter.POPULAR))
        })


        // See All Top Rated Recycler View
        tvTopRatedSeeAll.setOnClickListener(View.OnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToMoviesSeeAll(MovieFilter.TOP_RATED))
        })
    }

}