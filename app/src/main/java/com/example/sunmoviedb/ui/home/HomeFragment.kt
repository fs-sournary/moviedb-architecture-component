package com.example.sunmoviedb.ui.home

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.navigation.ActivityNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sunmoviedb.R
import com.example.sunmoviedb.data.CategoryMovie
import com.example.sunmoviedb.data.DiscoverMovie
import com.example.sunmoviedb.data.Genre
import com.example.sunmoviedb.databinding.FragmentHomeBinding
import com.example.sunmoviedb.ui.BaseFragment
import com.example.sunmoviedb.ui.home.adapter.categorymovie.CategoryMovieAdapter
import com.example.sunmoviedb.ui.home.adapter.discovermovie.GenreMovieAdapter
import com.example.sunmoviedb.ui.movieoption.MovieOptionBottomSheetDialogFragment
import com.example.sunmoviedb.widget.CategoryMovieItemDecoration
import com.example.sunmoviedb.widget.GenreMovieItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_home_genre_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.core.util.Pair as UtilPair

/**
 * Create on 4/3/19 by Sang
 * Description:
 **/
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private var checkedGenreText: AppCompatTextView? = null
    private lateinit var mostPopularMovieAdapter: CategoryMovieAdapter
    private lateinit var genreMovieAdapter: GenreMovieAdapter
    private lateinit var nowPlayingMovieAdapter: CategoryMovieAdapter
    private lateinit var topRateMovieAdapter: CategoryMovieAdapter

    override val viewModel: HomeViewModel by viewModel()

    override val layoutId: Int = R.layout.fragment_home

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMostPopularMovies()
        setupGenreMovies()
        setupNowPlayingMovies()
        setupTopRateMovies()
        setupViewModel()
    }

    private fun setupMostPopularMovies() {
        mostPopularMovieAdapter = CategoryMovieAdapter(object : CategoryMovieListener {
            override fun retry() {
                viewModel.retryGetPopularMovies()
            }

            override fun onItemClicked(v: View, movieId: Int) {
                startMovieDetail(v, movieId)
            }

            override fun onItemLongClicked(movie: CategoryMovie): Boolean {
                val transaction =
                    activity?.supportFragmentManager?.beginTransaction() ?: return false
                MovieOptionBottomSheetDialogFragment.newInstance(movie)
                    .show(transaction, MovieOptionBottomSheetDialogFragment.TAG)
                return true
            }
        })
        val mostPopularRecyclerView =
            most_popular_movie_layout.findViewById<RecyclerView>(R.id.category_movie_recycler)
        mostPopularRecyclerView.adapter = mostPopularMovieAdapter
        mostPopularRecyclerView.addItemDecoration(CategoryMovieItemDecoration())

    }

    private fun setupGenreMovies() {
        genreMovieAdapter = GenreMovieAdapter(object : DiscoverMovieListener {
            override fun onItemClicked(v: View, movieId: Int) {
                startMovieDetail(v, movieId)
            }

            override fun onItemLongClicked(movie: DiscoverMovie): Boolean {
                val transaction =
                    activity?.supportFragmentManager?.beginTransaction() ?: return false
                MovieOptionBottomSheetDialogFragment.newInstance(movie)
                    .show(transaction, MovieOptionBottomSheetDialogFragment.TAG)
                return true
            }
        })
        val layoutManager = GridLayoutManager(
            context, 2, GridLayoutManager.HORIZONTAL, false
        ).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (val viewType = genreMovieAdapter.getItemViewType(position)) {
                        R.layout.item_big_genre_movie -> BIG_GENRE_MOVIE_SPAN_COUNT
                        R.layout.item_small_genre_movie -> SMALL_GENRE_MOVIE_SPAN_COUNT
                        else -> throw IllegalStateException("Unknown view type: $viewType")
                    }
                }
            }
        }
        val genreMovieRecyclerView =
            genre_movie_layout.findViewById<RecyclerView>(R.id.genre_movie_recycler)
        genreMovieRecyclerView.adapter = genreMovieAdapter
        genreMovieRecyclerView.layoutManager = layoutManager
        genreMovieRecyclerView.addItemDecoration(GenreMovieItemDecoration())
    }

    private fun setupNowPlayingMovies() {
        nowPlayingMovieAdapter = CategoryMovieAdapter(object : CategoryMovieListener {
            override fun retry() {
                viewModel.retryGetNowPlayingMovies()
            }

            override fun onItemClicked(v: View, movieId: Int) {
                startMovieDetail(v, movieId)
            }

            override fun onItemLongClicked(movie: CategoryMovie): Boolean {
                val transaction =
                    activity?.supportFragmentManager?.beginTransaction() ?: return false
                MovieOptionBottomSheetDialogFragment.newInstance(movie)
                    .show(transaction, MovieOptionBottomSheetDialogFragment.TAG)
                return true
            }
        })
        val nowPlayingRecyclerView =
            now_playing_movie_layout.findViewById<RecyclerView>(R.id.category_movie_recycler)
        nowPlayingRecyclerView.adapter = nowPlayingMovieAdapter
        nowPlayingRecyclerView.addItemDecoration(CategoryMovieItemDecoration())
    }

    private fun setupTopRateMovies() {
        topRateMovieAdapter = CategoryMovieAdapter(object : CategoryMovieListener {
            override fun retry() {
                viewModel.retryGetTopRateMovies()
            }

            override fun onItemClicked(v: View, movieId: Int) {
                startMovieDetail(v, movieId)
            }

            override fun onItemLongClicked(movie: CategoryMovie): Boolean {
                val transaction =
                    activity?.supportFragmentManager?.beginTransaction() ?: return false
                MovieOptionBottomSheetDialogFragment.newInstance(movie)
                    .show(transaction, MovieOptionBottomSheetDialogFragment.TAG)
                return true
            }
        })
        val topRateRecyclerView =
            top_rated_movie_layout.findViewById<RecyclerView>(R.id.category_movie_recycler)
        topRateRecyclerView.adapter = topRateMovieAdapter
        topRateRecyclerView.addItemDecoration(CategoryMovieItemDecoration())
    }

    private fun startMovieDetail(v: View, movieId: Int) {
        activity?.apply {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, UtilPair.create(v, ViewCompat.getTransitionName(v))
            )
            val extras = ActivityNavigator.Extras.Builder().setActivityOptions(options).build()
            val action = HomeFragmentDirections.actionHomeToMovieDetail(movieId)
            findNavController().navigate(action, extras)
        }
    }

    private fun setupViewModel() {
        viewModel.apply {
            // Most popular movies
            popularMovies.observe(viewLifecycleOwner, Observer {
                mostPopularMovieAdapter.submitList(it)
            })
            popularNetworkState.observe(viewLifecycleOwner, Observer {
                mostPopularMovieAdapter.setNetworkState(it)
            })
            // Genre movies
            genres.observe(viewLifecycleOwner, Observer {
                val genreTextPadding = resources.getDimensionPixelOffset(R.dimen.dp_12)
                it.forEachIndexed { index, genre ->
                    val genreText = createGenreText(genre, genreTextPadding)
                    genre_linear.addView(genreText)
                    // Load genre categoryMovie with first genreId and no load again when rotating screen.
                    if (index == 0 && viewModel.isLoadFirstGenreMovie) {
                        viewModel.showGenreId(it[0].id)
                        genreText.isSelected = true
                        checkedGenreText = genreText
                        viewModel.isLoadFirstGenreMovie = false
                    }
                }
            })
            genreMovies.observe(viewLifecycleOwner, Observer { genreMovieAdapter.submitList(it) })
            // Now playing movies
            nowPlayingMovies.observe(viewLifecycleOwner, Observer {
                nowPlayingMovieAdapter.submitList(it)
            })
            nowPlayingNetworkState?.observe(viewLifecycleOwner, Observer {
                nowPlayingMovieAdapter.setNetworkState(it)
            })
            // Top rate movies
            topRateMovies.observe(viewLifecycleOwner, Observer {
                topRateMovieAdapter.submitList(it)
            })
            topRateNetworkState?.observe(viewLifecycleOwner, Observer {
                topRateMovieAdapter.setNetworkState(it)
            })
        }
    }

    private fun createGenreText(genre: Genre, padding: Int): AppCompatTextView {
        val genreText = AppCompatTextView(context).apply {
            text = genre.name
            setPadding(padding, padding, padding, padding)
            setTypeface(typeface, Typeface.BOLD)
            setBackgroundResource(R.drawable.bg_genre_item)
            // Switch fragment by BottomNavigationView, not save view hierarchy but data like ViewModel is still retained.
            if (genre.id == viewModel.genreId.value) {
                isSelected = true
                checkedGenreText = this
            }
        }
        genreText.setOnClickListener {
            // Make previous clicked item has unselected state
            checkedGenreText?.apply { isSelected = false }
            checkedGenreText = genreText
            // Make current clicked item has selected state
            genreText.isSelected = true
            viewModel.showGenreId(genre.id)
        }
        return genreText
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }

    companion object {

        const val BIG_GENRE_MOVIE_SPAN_COUNT = 2
        const val SMALL_GENRE_MOVIE_SPAN_COUNT = 1
    }
}
