package com.example.moviesapp.presentation.Adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesapp.MainActivity
import com.example.moviesapp.R
import com.example.moviesapp.data.models.MovieTrailersResponse
import com.example.moviesapp.data.models.PlayingNowResponse
import com.example.moviesapp.utils.Videos
import kotlinx.android.synthetic.main.playing_now_item.view.*
import kotlinx.android.synthetic.main.trailers_item.view.*

class TrailersAdapter(private val videos: Videos): RecyclerView.Adapter<TrailersAdapter.MyViewHolder>() {
    private var trailerList = listOf<MovieTrailersResponse.Result>()

    class MyViewHolder(itemView: View, private val videos: Videos): RecyclerView.ViewHolder(itemView) {
        fun bindData(trailer: MovieTrailersResponse.Result) {
            itemView.cvPlayingNowCard.setOnClickListener(){
                videos.playVideos(trailer.key)
            }
        }
    }

    fun setData(trailers: List<MovieTrailersResponse.Result>) {
        trailerList = trailers
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.trailers_item,
                parent,
                false
            )
        return MyViewHolder(view, videos)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(trailerList[position])
    }

    override fun getItemCount(): Int {
        return trailerList.size
    }
}