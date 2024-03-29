package com.example.moviesapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesapp.BuildConfig
import com.example.moviesapp.R
import com.example.moviesapp.data.models.MovieReviewsResponse
import kotlinx.android.synthetic.main.review_item.view.*

class ReviewSeeAllAdapter: RecyclerView.Adapter<ReviewSeeAllAdapter.MyViewHolder>() {
    private var reviewsList = listOf<MovieReviewsResponse.Result>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindData(review: MovieReviewsResponse.Result){
            itemView.tvMovieDetailsReviewsUsernameItem.text = review.author
            itemView.ivMovieDetailsReviewsUserPhotoItem.load("${BuildConfig.API_IMAGE_LOADING_URL}${review.author_details.avatar_path}")
            itemView.tvReviewerRateItem.text = review.author_details.rating.toString()
            itemView.tvMovieDetailsReviewDateItem.text = review.created_at
            itemView.tvMovieDetailsReviewsUserReviewItem.text = review.content
        }
    }

    fun setData(reviews: List<MovieReviewsResponse.Result>){
        reviewsList = reviews
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.review_item,
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(reviewsList[position])
    }

    override fun getItemCount(): Int {
        return  reviewsList.size
    }
}