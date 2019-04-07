package com.example.sunmoviedb.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.sunmoviedb.api.ApiConstants
import com.example.sunmoviedb.api.MovieApi
import com.example.sunmoviedb.data.CategoryMovie
import com.example.sunmoviedb.data.DetailMovie
import com.example.sunmoviedb.repository.movie.*
import com.example.sunmoviedb.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Create on 4/5/19 by Sang
 * Description:
 **/
class MovieRepository(
    private val movieApi: MovieApi,
    private val schedulerProvider: SchedulerProvider,
    private val compositeDisposable: CompositeDisposable
) {

    fun getDetailMovie(movieId: Int): RepoResult<LiveData<DetailMovie>> {
        val dataSource = DetailMovieDataSource(movieApi, schedulerProvider, compositeDisposable)
        dataSource.getMovie(movieId)
        return RepoResult(dataSource.movie)
    }

    fun getNowPlayingMovies(): RepoResult<LiveData<PagedList<CategoryMovie>>> {
        val sourceFactory = NowPlayingCategoryMovieDataSource.Factory(
            movieApi, schedulerProvider, compositeDisposable
        )
        val livePagedList = sourceFactory.toLiveData(ApiConstants.PAGE_LOAD_SIZE)
        val refreshState = switchMap(sourceFactory.sourceLiveData) { it.initialState }
        val networkState = switchMap(sourceFactory.sourceLiveData) { it.networkState }
        return RepoResult(
            data = livePagedList,
            initialState = refreshState,
            networkState = networkState,
            retry = { sourceFactory.sourceLiveData.value?.retry?.invoke() }
        )
    }

    fun getPopularMovies(): RepoResult<LiveData<PagedList<CategoryMovie>>> {
        val sourceFactory =
            PopularCategoryMovieDataSource.Factory(movieApi, schedulerProvider, compositeDisposable)
        val livePagedList = sourceFactory.toLiveData(ApiConstants.PAGE_LOAD_SIZE)
        val refreshState = switchMap(sourceFactory.sourceLiveData) { it.initialState }
        val networkState = switchMap(sourceFactory.sourceLiveData) { it.networkState }
        return RepoResult(
            data = livePagedList,
            initialState = refreshState,
            networkState = networkState,
            retry = { sourceFactory.sourceLiveData.value?.retry?.invoke() }
        )
    }

    fun getTopRatedMovies(): RepoResult<LiveData<PagedList<CategoryMovie>>> {
        val sourceFactory = TopRatedCategoryMovieDataSource.Factory(
            movieApi, schedulerProvider, compositeDisposable
        )
        val livePagedList = sourceFactory.toLiveData(ApiConstants.STARTED_PAGE)
        val refreshState = switchMap(sourceFactory.sourceLiveData) { it.initialState }
        val networkState = switchMap(sourceFactory.sourceLiveData) { it.networkState }
        return RepoResult(
            data = livePagedList,
            initialState = refreshState,
            networkState = networkState,
            retry = { sourceFactory.sourceLiveData.value?.retry?.invoke() }
        )
    }

    fun getUpComingMovies(): RepoResult<LiveData<PagedList<CategoryMovie>>> {
        val sourceFactory = UpComingCategoryMovieDataSource.Factory(
            movieApi, schedulerProvider, compositeDisposable
        )
        val livePagedList = sourceFactory.toLiveData(ApiConstants.PAGE_LOAD_SIZE)
        val refreshState = switchMap(sourceFactory.sourceLiveData) { it.initialState }
        val networkState = switchMap(sourceFactory.sourceLiveData) { it.networkState }
        return RepoResult(
            data = livePagedList,
            initialState = refreshState,
            networkState = networkState,
            retry = { sourceFactory.sourceLiveData.value?.retry?.invoke() }
        )
    }

    fun getSimilarMovies(movieId: Int): RepoResult<LiveData<PagedList<CategoryMovie>>> {
        val sourceFactory = SimilarMovieDataSource.Factory(
            movieApi, movieId, schedulerProvider, compositeDisposable
        )
        val livePagedList = sourceFactory.toLiveData(ApiConstants.PAGE_LOAD_SIZE)
        val refreshState = switchMap(sourceFactory.sourceLiveData) { it.initialState }
        val networkState = switchMap(sourceFactory.sourceLiveData) { it.networkState }
        return RepoResult(
            data = livePagedList,
            initialState = refreshState,
            networkState = networkState,
            retry = { sourceFactory.sourceLiveData.value?.retry?.invoke() }
        )
    }

    fun getRecommendataionMovies(movieId: Int): RepoResult<LiveData<PagedList<CategoryMovie>>> {
        val sourceFactory = RecommendationMovieDataSource.Factory(
            movieApi, movieId, schedulerProvider, compositeDisposable
        )
        val livePagedList = sourceFactory.toLiveData(ApiConstants.PAGE_LOAD_SIZE)
        val refreshState = switchMap(sourceFactory.sourceLiveData) { it.initialState }
        val networkState = switchMap(sourceFactory.sourceLiveData) { it.networkState }
        return RepoResult(
            data = livePagedList,
            initialState = refreshState,
            networkState = networkState,
            retry = { sourceFactory.sourceLiveData.value?.retry?.invoke() }
        )
    }

    fun clear() {
        compositeDisposable.clear()
    }
}
