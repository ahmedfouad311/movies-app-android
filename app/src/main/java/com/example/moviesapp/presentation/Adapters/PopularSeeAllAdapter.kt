

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesapp.R
import com.example.moviesapp.data.models.PopularResponse
import kotlinx.android.synthetic.main.popular_item.view.*
import kotlinx.android.synthetic.main.popular_see_all_item.view.*

class PopularSeeAllAdapter(val movieItemCallBack: (movieId: Long) -> Unit): RecyclerView.Adapter<PopularSeeAllAdapter.MyViewHolder>() {
    private var popularSeeAllMovies = listOf<PopularResponse.Result>()

    class MyViewHolder(itemView: View, val movieItemCallBack: (movieId: Long) -> Unit): RecyclerView.ViewHolder(itemView) {
        fun bindData(movie: PopularResponse.Result){
            itemView.tvMovieNamePopularSeeAll.text = movie.original_title
            itemView.tvDescriptionPopularSeeAll.text = movie.release_date
            itemView.tvPopularSeeAllRate.text = movie.vote_average.toString()
            itemView.tvPopularSeeAllRateCount.text = "(${movie.vote_count})"
            itemView.ivPopularSeeAll.load("https://image.tmdb.org/t/p/original/${movie.poster_path}")
            itemView.cvPopularSeeAllCard.setOnClickListener(){
                movieItemCallBack(movie.id.toLong())
            }
        }
    }

    fun setData(movies: List<PopularResponse.Result>){
        popularSeeAllMovies = movies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.popular_see_all_item,
            parent,
            false
        )
        return MyViewHolder(view, movieItemCallBack)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(popularSeeAllMovies[position])
    }

    override fun getItemCount(): Int {
        return popularSeeAllMovies.size
    }
}