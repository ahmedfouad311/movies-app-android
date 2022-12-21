

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.presentation.Adapters.PlayingNowSeeAllAdapter
import kotlinx.android.synthetic.main.fragment_playing_now_see_all.*

class PlayingNowSeeAll : Fragment() {
    private lateinit var playingNowSeeAllAdapter: PlayingNowSeeAllAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playing_now_see_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playingNowSeeAllAdapter = PlayingNowSeeAllAdapter()

        rvPlayingNowSeeAll.layoutManager = LinearLayoutManager(activity)
        rvPlayingNowSeeAll.adapter = playingNowSeeAllAdapter
    }

}