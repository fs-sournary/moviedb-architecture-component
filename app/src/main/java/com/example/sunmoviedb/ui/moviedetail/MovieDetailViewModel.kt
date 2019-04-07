package com.example.sunmoviedb.ui.moviedetail

import androidx.lifecycle.ViewModel
import com.example.sunmoviedb.repository.MovieRepository

/**
 * Created in 4/25/19 by Sang
 * Description:
 */
class MovieDetailViewModel(val movieId: Int, movieRepository: MovieRepository) : ViewModel() {

    private val movieDetailRepoResult = movieRepository.getDetailMovie(movieId)
    val movieDetail = movieDetailRepoResult.data

    private val similarMovieRepoResult = movieRepository.getSimilarMovies(movieId)
    val similarMovies = similarMovieRepoResult.data
    val similarMovieNetworkState = similarMovieRepoResult.networkState

    private val recommendationMovieRepoResult = movieRepository.getRecommendataionMovies(movieId)
    val recommendationMovies = recommendationMovieRepoResult.data
    val recommendationMovieNetworkState = recommendationMovieRepoResult.networkState
}
