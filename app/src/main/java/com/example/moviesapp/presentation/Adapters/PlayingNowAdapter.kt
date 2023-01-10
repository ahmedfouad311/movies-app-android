
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesapp.R
import com.example.moviesapp.data.models.PlayingNowResponse
import kotlinx.android.synthetic.main.playing_now_item.view.*

class PlayingNowAdapter : RecyclerView.Adapter<PlayingNowAdapter.MyViewHolder>() {
    private var playingNowMovies = listOf<PlayingNowResponse.Result>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(movie: PlayingNowResponse.Result) {
            itemView.tvMovieNamePlayingNow.text = movie.original_title
            itemView.tvDescriptionPlayingNow.text = movie.release_date
            itemView.tvMovieRatePlayingNow.text = movie.vote_average.toString()
            itemView.tvMovieRateCountPlayingNow.text = "(${movie.vote_count})"
            itemView.ivPlayingNow.load("https://image.tmdb.org/t/p/original/${movie.poster_path}")
        }
    }

    fun setData(movies: List<PlayingNowResponse.Result>) {
        playingNowMovies = movies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.playing_now_item,
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(playingNowMovies[position])
    }

    override fun getItemCount(): Int {
        return 8
    }
}