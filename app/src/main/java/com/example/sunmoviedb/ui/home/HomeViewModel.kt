package com.example.sunmoviedb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.sunmoviedb.data.DiscoverMovie
import com.example.sunmoviedb.repository.DiscoverRepository
import com.example.sunmoviedb.repository.GenreRepository
import com.example.sunmoviedb.repository.MovieRepository
import com.example.sunmoviedb.repository.NetworkState

/**
 * Created on 4/5/19 by Sang
 * Description:
 **/
class HomeViewModel(
    private val movieRepository: MovieRepository,
    private val genreRepository: GenreRepository,
    private val discoverRepository: DiscoverRepository
) : ViewModel() {

    // Most popular movies
    private val popularMovieRepoResult = movieRepository.getPopularMovies()
    private val popularInitialState = popularMovieRepoResult.initialState!!
    val popularMovies = popularMovieRepoResult.data
    val popularNetworkState = popularMovieRepoResult.networkState!!
    val isPopularMovies: LiveData<Boolean> = map(popularInitialState) { it == NetworkState.SUCCESS }

    // Movies by genre
    var isLoadFirstGenreMovie = true
    private val _genreId = MutableLiveData<Int>()
    val genreId: LiveData<Int> = _genreId
    private val genreMovieRepoResult = map(_genreId) { discoverRepository.getMovieByGenre(it) }
    val genreMovies: LiveData<PagedList<DiscoverMovie>> = switchMap(genreMovieRepoResult) {
        it.data
    }

    // Now playing movies
    private val nowPlayingMovieRepoResult = movieRepository.getNowPlayingMovies()
    val nowPlayingMovies = nowPlayingMovieRepoResult.data
    val nowPlayingNetworkState = nowPlayingMovieRepoResult.networkState

    // Top rate movies
    private val topRateMovieRepoResult = movieRepository.getTopRatedMovies()
    val topRateMovies = topRateMovieRepoResult.data
    val topRateNetworkState = topRateMovieRepoResult.networkState

    // Genre
    private val genreRepoResult = genreRepository.getGenre()
    val genres = genreRepoResult.data

    fun retryGetPopularMovies() {
        popularMovieRepoResult.retry?.invoke()
    }

    fun retryGetNowPlayingMovies(){
        nowPlayingMovieRepoResult.retry?.invoke()
    }

    fun retryGetTopRateMovies(){
        topRateMovieRepoResult.retry?.invoke()
    }

    fun showGenreId(genreId: Int) {
        if (_genreId.value != genreId) {
            _genreId.value = genreId
        }
    }

    fun clear() {
        movieRepository.clear()
        discoverRepository.clear()
        genreRepository.clear()
    }
}
