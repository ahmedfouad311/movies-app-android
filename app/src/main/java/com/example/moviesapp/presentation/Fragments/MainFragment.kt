

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.presentation.Adapters.PopularAdapter
import com.example.moviesapp.presentation.Adapters.TopRatedAdapter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    private lateinit var playingNowAdapter: PlayingNowAdapter
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var topRatedAdapter: TopRatedAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    // compactPadding (card view)
    // item decreator (recyclerview scrolling item cut)
    // view binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playingNowAdapter = PlayingNowAdapter()
        popularAdapter = PopularAdapter()
        topRatedAdapter = TopRatedAdapter()

        rvPlayingNow.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvPlayingNow.adapter = playingNowAdapter

        rvPopular.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvPopular.adapter = popularAdapter

        rvTopRated.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvTopRated.adapter = topRatedAdapter



        tvPlayingNowSeeAll.setOnClickListener(View.OnClickListener {
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController = navHostFragment.navController

            // Navigate using the IDs you defined in your Nav Graph
            navController.navigate(R.id.playingNowSeeAll)
        })

        tvPopularSeeAll.setOnClickListener(View.OnClickListener {
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController = navHostFragment.navController

            navController.navigate(R.id.popularSeeAll)
        })

        tvTopRatedSeeAll.setOnClickListener(View.OnClickListener {
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController = navHostFragment.navController

            navController.navigate(R.id.topRatedSeeAll)
        })
    }


}