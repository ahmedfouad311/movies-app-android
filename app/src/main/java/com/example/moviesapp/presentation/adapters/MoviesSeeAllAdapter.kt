package com.example.moviesapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesapp.BuildConfig
import com.example.moviesapp.R
import com.example.moviesapp.data.models.MoviesResponse
import kotlinx.android.synthetic.main.movies_see_all_item.view.*


class MoviesSeeAllAdapter(val movieItemCallBack: (movieId: Long) -> Unit): RecyclerView.Adapter<MoviesSeeAllAdapter.MyViewHolder>() {
    private var moviesSeeAllList = listOf<MoviesResponse.Result>()
    class MyViewHolder(itemView: View, val movieItemCallBack: (movieId: Long) -> Unit): RecyclerView.ViewHolder(itemView) {
        fun bindData(movie: MoviesResponse.Result){
            itemView.tvMovieNameMoviesSeeAll.text = movie.original_title
            itemView.tvDescriptionMoviesSeeAll.text = movie.release_date
            itemView.tvMoviesSeeAllRate.text = movie.vote_average.toString()
            itemView.tvMoviesSeeAllRateCount.text = "(${movie.vote_count})"
            itemView.ivMoviesSeeAll.load("${BuildConfig.API_IMAGE_LOADING_URL}${movie.poster_path}")
            itemView.cvMoviesSeeAllCard.setOnClickListener(){
                movieItemCallBack(movie.id.toLong())
            }
        }
    }

    fun setData(movies: List<MoviesResponse.Result>){
        moviesSeeAllList = movies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.movies_see_all_item,
            parent,
            false
        )

        return MyViewHolder(view, movieItemCallBack)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(moviesSeeAllList[position])
    }

    override fun getItemCount(): Int {
        return moviesSeeAllList.size
    }
}