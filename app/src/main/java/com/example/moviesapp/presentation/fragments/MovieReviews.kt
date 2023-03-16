package com.example.moviesapp.presentation.fragments

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
import com.example.moviesapp.presentation.adapters.ReviewSeeAllAdapter
import com.example.moviesapp.presentation.viewModels.ReviewsViewModel
import kotlinx.android.synthetic.main.fragment_movie_reviews.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieReviews : Fragment() {
    private val args by navArgs<MovieDetailsArgs>()
    private val reviewsViewModel by viewModel<ReviewsViewModel>()
    private lateinit var reviewSeeAllAdapter: ReviewSeeAllAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_reviews, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Args From Reviews", args.movieId.toString())

        reviewSeeAllAdapter = ReviewSeeAllAdapter()

        reviewsViewModel.getMovieReviews(args.movieId)
        reviewsViewModel.movieReviewsLiveData.observe(viewLifecycleOwner){
            if(it.results.isNotEmpty()){
                rvReviewsSeeAll.visibility = View.VISIBLE
                reviewSeeAllAdapter.setData(it.results)
                rvReviewsSeeAll.adapter = reviewSeeAllAdapter
                rvReviewsSeeAll.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            } else {
                rvReviewsSeeAll.visibility = View.GONE
                tvNoReviewsSeeAll.visibility = View.VISIBLE
            }
        }

        ivReviewsSeeAllBackIcon.setOnClickListener(){
            findNavController().navigate(MovieReviewsDirections.actionMovieReviewsToMovieDetails(args.movieId))
        }
    }
}