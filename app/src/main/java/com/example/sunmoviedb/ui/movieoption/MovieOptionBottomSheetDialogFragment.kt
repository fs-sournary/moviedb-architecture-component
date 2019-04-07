package com.example.sunmoviedb.ui.movieoption

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sunmoviedb.BR
import com.example.sunmoviedb.R
import com.example.sunmoviedb.api.ApiConstants
import com.example.sunmoviedb.data.CategoryMovie
import com.example.sunmoviedb.data.DiscoverMovie
import com.example.sunmoviedb.databinding.FragmentMovieOptionBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_movie_option.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * Created in 4/23/19 by Sang
 * Description:
 */
class MovieOptionBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var categoryMovie: CategoryMovie? = null
    private var discoverMovie: DiscoverMovie? = null

    private lateinit var binding: FragmentMovieOptionBinding

    private val viewModel: MovieOptionViewModel by viewModel {
        parametersOf(categoryMovie, discoverMovie)
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        dialog.setOnShowListener {
            if (it is BottomSheetDialog) {
                val designView =
                    it.findViewById<View>(R.id.design_bottom_sheet) ?: return@setOnShowListener
                designView.background = null
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        categoryMovie = arguments?.getParcelable(EXTRA_CATEGORY_MOVIE_OPTION)
        discoverMovie = arguments?.getParcelable(EXTRA_DISCOVER_MOVIE_OPTION)
        binding = FragmentMovieOptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setVariable(BR.viewModel, this@MovieOptionBottomSheetDialogFragment.viewModel)
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        categoryMovie?.also { showCategoryMovieInfo(it) }
        discoverMovie?.also { showDiscoverMovieInfo(it) }
    }

    private fun showCategoryMovieInfo(categoryMovie: CategoryMovie) {
        context?.apply {
            val actualPosterPath = ApiConstants.BASE_IMAGE_URL + categoryMovie.posterPath
            Glide.with(this)
                .load(actualPosterPath)
                .apply(RequestOptions.circleCropTransform())
                .into(poster_image)
        }
        title_text.text = categoryMovie.title
        release_date_text.text = categoryMovie.releaseDate
    }

    private fun showDiscoverMovieInfo(discoverMovie: DiscoverMovie) {
        context?.apply {
            val actualPosterPath = ApiConstants.BASE_IMAGE_URL + discoverMovie.posterPath
            Glide.with(this)
                .load(actualPosterPath)
                .apply(RequestOptions.circleCropTransform())
                .into(poster_image)
        }
        title_text.text = discoverMovie.title
        release_date_text.text = discoverMovie.releaseDate
    }

    companion object {

        const val TAG = "MovieOption"
        const val EXTRA_CATEGORY_MOVIE_OPTION = "com.example.sunmoviedb.EXTRA_CATEGORY_MOVIE_OPTION"
        const val EXTRA_DISCOVER_MOVIE_OPTION = "com.example.sunmoviedb.EXTRA_DISCOVER_MOVIE_OPTION"

        fun newInstance(movie: CategoryMovie): MovieOptionBottomSheetDialogFragment =
            MovieOptionBottomSheetDialogFragment().apply {
                arguments = Bundle().apply { putParcelable(EXTRA_CATEGORY_MOVIE_OPTION, movie) }
            }

        fun newInstance(movie: DiscoverMovie): MovieOptionBottomSheetDialogFragment =
            MovieOptionBottomSheetDialogFragment().apply {
                arguments = Bundle().apply { putParcelable(EXTRA_DISCOVER_MOVIE_OPTION, movie) }
            }
    }
}
