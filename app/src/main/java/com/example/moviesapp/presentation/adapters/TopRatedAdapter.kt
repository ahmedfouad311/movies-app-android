

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesapp.BuildConfig
import com.example.moviesapp.R
import com.example.moviesapp.data.models.MoviesResponse
import kotlinx.android.synthetic.main.top_rated_item.view.*

class TopRatedAdapter(val movieItemCallBack: (movieId: Long) -> Unit): RecyclerView.Adapter<TopRatedAdapter.MyViewHolder>() {
    private var topRatedMovies = listOf<MoviesResponse.Result>()

    class MyViewHolder(itemView: View, val movieItemCallBack: (movieId: Long) -> Unit): RecyclerView.ViewHolder(itemView) {
        fun bindData(movie: MoviesResponse.Result){
            itemView.tvMovieNameTopRated.text = movie.original_title
            itemView.tvDescriptionTopRated.text = movie.release_date
            itemView.tvMovieRateTopRated.text = movie.vote_average.toString()
            itemView.tvMovieRateCountTopRated.text = "(${movie.vote_count})"
            itemView.ivTopRated.load("${BuildConfig.API_IMAGE_LOADING_URL}${movie.poster_path}")
            itemView.cvTopRatedCard.setOnClickListener(){
                movieItemCallBack(movie.id.toLong())
            }
        }
    }

    fun setData(movies: List<MoviesResponse.Result>){
        topRatedMovies = movies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.top_rated_item,
            parent,
            false
        )
        return MyViewHolder(view, movieItemCallBack)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(topRatedMovies[position])
    }

    override fun getItemCount(): Int {
        return topRatedMovies.size / 2
    }
}