package com.example.moviesapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.data.models.MovieTrailersResponse
import kotlinx.android.synthetic.main.trailers_item.view.*

class TrailersAdapter(val movieItemCallBack: (videoKey: String) -> Unit): RecyclerView.Adapter<TrailersAdapter.MyViewHolder>() {
    private var trailerList = listOf<MovieTrailersResponse.Result>()

    class MyViewHolder(itemView: View, val movieItemCallBack: (videoKey: String) -> Unit): RecyclerView.ViewHolder(itemView) {
        fun bindData(trailer: MovieTrailersResponse.Result) {
            itemView.cvTrailer.setOnClickListener(){
                movieItemCallBack(trailer.key)
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
        return MyViewHolder(view, movieItemCallBack)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(trailerList[position])
    }

    override fun getItemCount(): Int {
        return trailerList.size
    }
}