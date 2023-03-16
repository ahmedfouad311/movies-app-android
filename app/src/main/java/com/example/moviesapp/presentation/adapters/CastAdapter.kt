package com.example.moviesapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesapp.BuildConfig
import com.example.moviesapp.R
import com.example.moviesapp.data.models.MovieCastResponse
import kotlinx.android.synthetic.main.cast_item.view.*

class CastAdapter: RecyclerView.Adapter<CastAdapter.MyViewHolder>() {
    private var castList = listOf<MovieCastResponse.Cast>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindData(cast: MovieCastResponse.Cast){
            itemView.ivCastPic.load("${BuildConfig.API_IMAGE_LOADING_URL}${cast.profile_path}")
            itemView.tvCastRealName.text = cast.original_name
            itemView.tvCastMovieName.text = cast.name
        }
    }

    fun setData(casts: List<MovieCastResponse.Cast>){
        castList = casts
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.cast_item,
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(castList[position])
    }

    override fun getItemCount(): Int {
        return castList.size
    }
}