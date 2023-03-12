package com.example.moviesapp.presentation.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.moviesapp.MainActivity
import com.example.moviesapp.R
import com.example.moviesapp.data.models.MovieCastResponse
import com.example.moviesapp.data.models.MovieDetailsResponse
import com.example.moviesapp.data.models.MovieReviewsResponse
import com.example.moviesapp.data.models.MovieTrailersResponse
import com.example.moviesapp.presentation.Adapters.CastAdapter
import com.example.moviesapp.presentation.Adapters.TrailersAdapter
import com.example.moviesapp.presentation.viewModels.DetailsViewModel
import com.example.moviesapp.presentation.viewModels.MainViewModel
import com.example.moviesapp.presentation.viewModels.ReviewsViewModel
import com.example.moviesapp.utils.Videos
import io.reactivex.rxjava3.internal.operators.maybe.MaybeToPublisher.instance
import io.reactivex.rxjava3.internal.util.ListAddBiConsumer.instance
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.trailers_item.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.format.DateTimeFormatter

class MovieDetails : Fragment(), Videos {
    private val viewModel by viewModel<DetailsViewModel>()
    private val reviewsViewModel by viewModel<ReviewsViewModel>()
    private val args by navArgs<MovieDetailsArgs>()
    private lateinit var trailersAdapter: TrailersAdapter
    private lateinit var castAdapter: CastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Args from Details", "args ${args.movieId}")
        viewModel.getMovieDetails(args.movieId)
        viewModel.movieDetailsLiveDataError.observe(viewLifecycleOwner){
            Log.d("Error", it.toString())
        }
        viewModel.movieDetailsLiveData.observe(viewLifecycleOwner){
            if(it is MovieDetailsResponse){
                ivMoviePosterDetails.load("https://image.tmdb.org/t/p/original/${it.poster_path}")
                tvDetailsMovieName.text = it.title
                tvMovieDetailRatingNumber.text = "(${it.vote_count}  reviews)"
                tvMovieDateDetails.text = it.release_date
                tvMovieDetailsOverviewText.text = it.overview
                val list = mutableListOf<String>()
                for(i in it.genres){
                    list.add(i.name)
                }
                tvMovieCategoryDetails.text = list.toString()
            }
        }


        trailersAdapter = TrailersAdapter(movieItemCallBack = {
            val urlIntent = Intent(Intent.ACTION_VIEW)
            urlIntent.data = Uri.parse("https://www.youtube.com/watch?v=${it}")
            requireActivity().startActivity(urlIntent)
        })
        viewModel.getMovieTrailers(args.movieId)
        viewModel.movieTrailersLiveData.observe(viewLifecycleOwner){
            if(it is MovieTrailersResponse){
                Log.d("Trailers", it.results.toString())
                trailersAdapter.setData(it.results)
                rvTrailers.adapter = trailersAdapter
            }
        }
        rvTrailers.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)


        // Reviews
        reviewsViewModel.getMovieReviews(args.movieId)
        reviewsViewModel.movieReviewsLiveData.observe(viewLifecycleOwner){
            if(it.results.isNotEmpty()){
                tvMovieDetailsReviewsUsername.visibility = View.VISIBLE
                tvMovieDetailsReviewsUsername.text = it.results[0].author

                tvMovieDetailsReviewDate.visibility = View.VISIBLE
                tvMovieDetailsReviewDate.text = it.results[0].created_at

                tvMovieDetailsReviewsUserReview.visibility = View.VISIBLE
                tvMovieDetailsReviewsUserReview.text = it.results[0].content

                tvReviewerRate.visibility = View.VISIBLE
                tvReviewerRate.text = it.results[0].author_details.rating.toString()

                ivMovieDetailsReviewsUserPhoto.visibility = View.VISIBLE
                ivMovieDetailsReviewsUserPhoto.load("https://image.tmdb.org/t/p/original/${it.results[0].author_details.avatar_path}")

                ivReviewerRate.visibility = View.VISIBLE

            } else{
                tvNoReviews.visibility = View.VISIBLE
                tvMovieDetailsReviewsUsername.visibility = View.GONE
                tvMovieDetailsReviewDate.visibility = View.GONE
                ivReviewerRate.visibility = View.GONE
                tvMovieDetailsReviewsUserReview.visibility = View.GONE
                tvReviewerRate.visibility = View.GONE
                ivMovieDetailsReviewsUserPhoto.visibility = View.GONE
            }
        }
        reviewsViewModel.movieReviewsLiveDataError.observe(viewLifecycleOwner){
            Log.d("Reviews Error", "Errrooooorrrr $it")
        }

        tvMovieDetailsReviewsSeeAllText.setOnClickListener(){
            findNavController().navigate(MovieDetailsDirections.actionMovieDetailsToMovieReviews(args.movieId))
        }


        // Cast
        castAdapter = CastAdapter()
        viewModel.getMovieCast(args.movieId)
        viewModel.movieCastLiveData.observe(viewLifecycleOwner){
            Log.d("Success", "Cast Data ${it.cast}")
            if(it.cast!!.isNotEmpty()){
                rvCast.visibility = View.VISIBLE
                castAdapter.setData(it.cast)
                rvCast.adapter = castAdapter
                rvCast.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            } else {
                rvCast.visibility = View.GONE
                tvNoCasts.visibility = View.VISIBLE
            }
        }

        viewModel.movieCastLiveDataError.observe(viewLifecycleOwner){
            Log.d("Fail", "Cast Error $it")
        }
    }

    override fun playVideos(key: String) {
        val urlIntent = Intent(Intent.ACTION_VIEW)
        urlIntent.data = Uri.parse("https://www.youtube.com/watch?v=${key}")
        requireActivity().startActivity(urlIntent)
    }
}