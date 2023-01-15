package com.example.moviesapp.presentation.Fragments

import PlayingNowAdapter
import PopularAdapter
import TopRatedAdapter
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.moviesapp.R
import com.example.moviesapp.data.models.MovieCategoryResponse
import com.example.moviesapp.data.models.PlayingNowResponse
import com.example.moviesapp.data.models.PopularResponse
import com.example.moviesapp.data.models.TopRatedResponse
import com.example.moviesapp.presentation.viewModels.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.playing_now_item.*
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
        playingNowAdapter = PlayingNowAdapter()

        viewModel.getNowPlaying()
        viewModel.result.observe(viewLifecycleOwner) {
            if(it is PlayingNowResponse){
                Log.d(MainFragment::class.java.simpleName, "Playing Now: ${it.results}")
                playingNowAdapter.setData(it.results)
                rvPlayingNow.adapter = playingNowAdapter
            }
        }

        rvPlayingNow.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        var snapHelperPlayingNow: SnapHelper = LinearSnapHelper()
        snapHelperPlayingNow.attachToRecyclerView(rvPlayingNow)


        // Popular Recycler View
        popularAdapter = PopularAdapter()
        viewModel.getPopular()
        viewModel.result2.observe(viewLifecycleOwner){
            if(it is PopularResponse){
                Log.d(MainFragment::class.java.simpleName, "Popular: ${it.results}")
                popularAdapter.setData(it.results)
                rvPopular.adapter = popularAdapter
            }
        }

        rvPopular.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)




        // Top Rated Recycler View
        topRatedAdapter = TopRatedAdapter()
        viewModel.getTopRated()
        viewModel.result3.observe(viewLifecycleOwner){
            if(it is TopRatedResponse){
                Log.d(MainFragment::class.java.simpleName, "Top Rated: ${it.results}")
                topRatedAdapter.setData(it.results)
                rvTopRated.adapter = topRatedAdapter
            }
        }

        rvTopRated.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)


        viewModel.getCategories()
        viewModel.resultCategory.observe(viewLifecycleOwner){
            if(it is MovieCategoryResponse){
                Log.d(MainFragment::class.java.simpleName, "Categories: ${it.genres}")
                val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("Categories", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
//                editor.apply{
//                    putString()
//                }
            }
        }




        // See All Button Playing Now
        tvPlayingNowSeeAll.setOnClickListener(View.OnClickListener {
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController = navHostFragment.navController

            // Navigate using the IDs you defined in your Nav Graph
            navController.navigate(R.id.playingNowSeeAll)
        })


        // See All Button Popular Recycler View
        tvPopularSeeAll.setOnClickListener(View.OnClickListener {
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController = navHostFragment.navController

            navController.navigate(R.id.popularSeeAll)
        })


        // See All Top Rated Recycler View
        tvTopRatedSeeAll.setOnClickListener(View.OnClickListener {
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController = navHostFragment.navController

            navController.navigate(R.id.topRatedSeeAll)
        })
    }


}