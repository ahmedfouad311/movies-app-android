
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesapp.BuildConfig
import com.example.moviesapp.R
import com.example.moviesapp.data.models.MoviesResponse
import kotlinx.android.synthetic.main.playing_now_item.view.*

class PlayingNowAdapter(val movieItemCallBack: (movieId: Long) -> Unit) : RecyclerView.Adapter<PlayingNowAdapter.MyViewHolder>() {
    private var playingNowMovies = listOf<MoviesResponse.Result>()

    class MyViewHolder(itemView: View, val movieItemCallBack : (movieId : Long) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bindData(movie: MoviesResponse.Result) {
            itemView.tvMovieNamePlayingNow.text = movie.original_title
            itemView.tvMovieRatePlayingNow.text = movie.vote_average.toString()
            itemView.tvDescriptionPlayingNow.text = movie.release_date
            itemView.tvMovieRateCountPlayingNow.text = "(${movie.vote_count})"
            itemView.ivPlayingNow.load("${BuildConfig.API_IMAGE_LOADING_URL}${movie.poster_path}")
            itemView.cvPlayingNowCard.setOnClickListener(){
                movieItemCallBack(movie.id.toLong())
            }
        }
    }

    fun setData(movies: List<MoviesResponse.Result>) {
        playingNowMovies = movies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.playing_now_item,
            parent,
            false
        )
        return MyViewHolder(view, movieItemCallBack)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(playingNowMovies[position])
    }

    override fun getItemCount(): Int {
        return playingNowMovies.size / 2
    }
}