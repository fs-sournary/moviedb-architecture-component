package com.example.sunmoviedb.ui.moviedetail

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.sunmoviedb.R
import com.example.sunmoviedb.data.CategoryMovie
import com.example.sunmoviedb.databinding.FragmentMovieDetailBinding
import com.example.sunmoviedb.ui.BaseFragment
import com.example.sunmoviedb.ui.home.CategoryMovieListener
import com.example.sunmoviedb.ui.home.adapter.categorymovie.CategoryMovieAdapter
import com.example.sunmoviedb.widget.CategoryMovieItemDecoration
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * Created in 4/25/19 by Sang
 * Description:
 */
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, MovieDetailViewModel>() {

    private lateinit var similarMovieAdapter: CategoryMovieAdapter
    private lateinit var recommendationAdapter: CategoryMovieAdapter

    private val args by navArgs<MovieDetailFragmentArgs>()
    private var movieId: Int = -1

    override val viewModel: MovieDetailViewModel by viewModel { parametersOf(movieId) }

    override val layoutId: Int = R.layout.fragment_movie_detail

    override fun setupBeforeViewModelCreated(view: View, savedInstanceState: Bundle?) {
        super.setupBeforeViewModelCreated(view, savedInstanceState)
        movieId = args.movieId
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // shared element
        ViewCompat.setTransitionName(viewBinding.posterImage, movieId.toString())
        setupSimilarMovies()
        setupRecommendationMovies()
        setupViewModel()
    }

    private fun setupSimilarMovies() {
        similarMovieAdapter = CategoryMovieAdapter(object : CategoryMovieListener {
            override fun retry() {

            }

            override fun onItemClicked(v: View, movieId: Int) {

            }

            override fun onItemLongClicked(movie: CategoryMovie): Boolean {
                return true
            }
        })
        similar_movie_recycler.adapter = similarMovieAdapter
        similar_movie_recycler.addItemDecoration(CategoryMovieItemDecoration())
    }

    private fun setupRecommendationMovies() {
        recommendationAdapter = CategoryMovieAdapter(object : CategoryMovieListener {
            override fun retry() {

            }

            override fun onItemClicked(v: View, movieId: Int) {

            }

            override fun onItemLongClicked(movie: CategoryMovie): Boolean {

                return true
            }
        })
        recommendation_recycler.adapter = recommendationAdapter
        recommendation_recycler.addItemDecoration(CategoryMovieItemDecoration())
    }

    private fun setupViewModel() {
        viewModel.apply {
            similarMovies.observe(viewLifecycleOwner, Observer {
                similarMovieAdapter.submitList(it)
            })
            similarMovieNetworkState?.observe(viewLifecycleOwner, Observer {
                similarMovieAdapter.setNetworkState(it)
            })
            recommendationMovies.observe(viewLifecycleOwner, Observer {
                recommendationAdapter.submitList(it)
            })
            recommendationMovieNetworkState?.observe(viewLifecycleOwner, Observer {
                recommendationAdapter.setNetworkState(it)
            })
        }
    }
}
