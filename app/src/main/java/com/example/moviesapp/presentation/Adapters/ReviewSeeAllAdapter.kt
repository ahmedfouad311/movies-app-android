package com.example.moviesapp.presentation.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesapp.R
import com.example.moviesapp.data.models.MovieReviewsResponse
import kotlinx.android.synthetic.main.review_item.view.*

class ReviewSeeAllAdapter: RecyclerView.Adapter<ReviewSeeAllAdapter.MyViewHolder>() {
    private var reviewsList = listOf<MovieReviewsResponse.Result>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindData(review: MovieReviewsResponse.Result){
            itemView.tvMovieDetailsReviewsUsernameItem.text = review.author
            if(review.author_details.avatar_path == null){
                itemView.ivMovieDetailsReviewsUserPhotoItem2.visibility = View.VISIBLE
                itemView.ivMovieDetailsReviewsUserPhotoItem.visibility = View.GONE
            } else{
                itemView.ivMovieDetailsReviewsUserPhotoItem.visibility = View.VISIBLE
                itemView.ivMovieDetailsReviewsUserPhotoItem.load("https://image.tmdb.org/t/p/original/${review.author_details.avatar_path}")
                itemView.ivMovieDetailsReviewsUserPhotoItem2.visibility = View.GONE
            }
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