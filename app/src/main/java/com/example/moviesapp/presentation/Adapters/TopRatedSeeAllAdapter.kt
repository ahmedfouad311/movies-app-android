

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesapp.R
import com.example.moviesapp.data.models.TopRatedResponse
import kotlinx.android.synthetic.main.top_rated_see_all_item.view.*

class TopRatedSeeAllAdapter: RecyclerView.Adapter<TopRatedSeeAllAdapter.MyViewHolder>() {
    private var topRatedSeeAllMovies = listOf<TopRatedResponse.Result>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindData(movie: TopRatedResponse.Result){
            itemView.tvMovieNameTopRatedSeeAll.text = movie.original_title
            itemView.tvDescriptionTopRatedSeeAll.text = movie.release_date
            itemView.tvTopRatedSeeAllRate.text = movie.vote_average.toString()
            itemView.tvTopRatedSeeAllRateCount.text = "(${movie.vote_count})"
            itemView.ivTopRatedSeeAll.load("https://image.tmdb.org/t/p/original/${movie.poster_path}")
        }
    }

    fun setData(movies: List<TopRatedResponse.Result>){
        topRatedSeeAllMovies = movies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.top_rated_see_all_item,
            parent,
            false
        )

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(topRatedSeeAllMovies[position])
    }

    override fun getItemCount(): Int {
        return topRatedSeeAllMovies.size
    }
}