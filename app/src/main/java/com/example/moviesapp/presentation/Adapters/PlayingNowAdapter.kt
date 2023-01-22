
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesapp.R
import com.example.moviesapp.data.models.MovieCategoryResponse
import com.example.moviesapp.data.models.PlayingNowResponse
import kotlinx.android.synthetic.main.playing_now_item.view.*

class PlayingNowAdapter : RecyclerView.Adapter<PlayingNowAdapter.MyViewHolder>() {
    private var playingNowMovies = listOf<PlayingNowResponse.Result>()
    lateinit var myListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        myListener = listener
    }

    @Suppress("DEPRECATION")
    class MyViewHolder(itemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
        fun bindData(movie: PlayingNowResponse.Result) {
            val preferences = itemView.context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            val prefList = preferences.all


            itemView.tvMovieNamePlayingNow.text = movie.original_title
//            itemView.tvDescriptionPlayingNow.text = movie.release_date
            itemView.tvMovieRatePlayingNow.text = movie.vote_average.toString()
            itemView.tvMovieRateCountPlayingNow.text = "(${movie.vote_count})"
            itemView.ivPlayingNow.load("https://image.tmdb.org/t/p/original/${movie.poster_path}")
//            itemView.ivCardBackground.load("https://image.tmdb.org/t/p/original/${movie.poster_path}")
//            for(i in movie.genre_ids){
//                for(j in prefList){
//                    if(prefList.containsKey(i.toString())){
//                        itemView.tvDescriptionPlayingNow.text = prefList.get(j.value).toString()
//                    }
//                }
//            }
            itemView.tvDescriptionPlayingNow.text = prefList.get(movie.genre_ids.toString()).toString()
//            val genreNames: List<String> = movie.genre_ids.map {
//
//            }
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
        return MyViewHolder(view, myListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(playingNowMovies[position])
    }

    override fun getItemCount(): Int {
        return 8
    }
}