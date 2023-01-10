

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesapp.R
import com.example.moviesapp.data.models.PlayingNowResponse
import kotlinx.android.synthetic.main.playing_now_see_all_item.view.*

class PlayingNowSeeAllAdapter: RecyclerView.Adapter<PlayingNowSeeAllAdapter.MyViewHolder>() {
    private var playingNowSeeAllMovies = listOf<PlayingNowResponse.Result>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindData(movie: PlayingNowResponse.Result){
            itemView.tvMovieNamePlayingNowSeeAll.text = movie.original_title
            itemView.tvDescriptionPlayingNowSeeAll.text = movie.release_date
            itemView.tvPlayingNowSeeAllRate.text = movie.vote_average.toString()
            itemView.tvPlayingNowSeeAllRateCount.text = "(${movie.vote_count})"
            itemView.ivPlayingNowSeeAll.load("https://image.tmdb.org/t/p/original/${movie.poster_path}")
        }
    }

    fun setData(movies: List<PlayingNowResponse.Result>){
        playingNowSeeAllMovies = movies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.playing_now_see_all_item,
            parent,
            false
        )

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(playingNowSeeAllMovies[position])
    }

    override fun getItemCount(): Int {
        return playingNowSeeAllMovies.size
    }
}