
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesapp.R
import com.example.moviesapp.data.models.MovieCategoryResponse
import com.example.moviesapp.data.models.PlayingNowResponse
import com.example.moviesapp.utils.deserialize
import kotlinx.android.synthetic.main.playing_now_item.view.*

class PlayingNowAdapter(val movieItemCallBack: (movieId: Long) -> Unit) : RecyclerView.Adapter<PlayingNowAdapter.MyViewHolder>() {
    private var playingNowMovies = listOf<PlayingNowResponse.Result>()

    class MyViewHolder(itemView: View, val movieItemCallBack : (movieId : Long) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bindData(movie: PlayingNowResponse.Result) {
            itemView.tvMovieNamePlayingNow.text = movie.original_title
            itemView.tvMovieRatePlayingNow.text = movie.vote_average.toString()
            itemView.tvDescriptionPlayingNow.text = movie.release_date
            itemView.tvMovieRateCountPlayingNow.text = "(${movie.vote_count})"
            itemView.ivPlayingNow.load("https://image.tmdb.org/t/p/original/${movie.poster_path}")
            itemView.cvPlayingNowCard.setOnClickListener(){
                movieItemCallBack(movie.id.toLong())
            }
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
        return MyViewHolder(view, movieItemCallBack)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(playingNowMovies[position])
    }

    override fun getItemCount(): Int {
        return playingNowMovies.size / 2
    }
}