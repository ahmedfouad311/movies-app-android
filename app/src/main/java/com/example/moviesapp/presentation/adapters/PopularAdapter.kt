

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesapp.BuildConfig
import com.example.moviesapp.R
import com.example.moviesapp.data.models.MoviesResponse
import kotlinx.android.synthetic.main.playing_now_item.view.*
import kotlinx.android.synthetic.main.popular_item.view.*

class PopularAdapter(val movieItemCallBack: (movieId: Long) -> Unit): RecyclerView.Adapter<PopularAdapter.MyViewHolder>() {
    private var popularMovies = listOf<MoviesResponse.Result>()

    class MyViewHolder(itemView: View, val movieItemCallBack: (movieId: Long) -> Unit): RecyclerView.ViewHolder(itemView) {
        fun bindData(movie: MoviesResponse.Result){
            itemView.tvMovieNamePopular.text = movie.original_title
            itemView.tvDescriptionPopular.text = movie.release_date
            itemView.tvMovieRatePopular.text = movie.vote_average.toString()
            itemView.tvMovieRateCountPopular.text = "(${movie.vote_count})"
            itemView.ivPopular.load("${BuildConfig.API_IMAGE_LOADING_URL}${movie.poster_path}")
            itemView.cvPopularCard.setOnClickListener(){
                movieItemCallBack(movie.id.toLong())
            }

        }
    }

    fun setData(movies: List<MoviesResponse.Result>){
        popularMovies = movies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.popular_item,
            parent,
            false
        )
        return MyViewHolder(view, movieItemCallBack)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(popularMovies[position])
    }

    override fun getItemCount(): Int {
        return popularMovies.size / 2
    }
}