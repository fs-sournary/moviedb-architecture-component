package com.example.sunmoviedb.repository.discover

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.sunmoviedb.api.ApiConstants
import com.example.sunmoviedb.api.DiscoverApi
import com.example.sunmoviedb.data.DiscoverMovie
import com.example.sunmoviedb.repository.BasePagingDataSource
import com.example.sunmoviedb.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created on 2019-04-21 by Sang
 * Description:
 **/
class GenreMovieDataSource(
    private val discoverApi: DiscoverApi,
    private val genreId: Int,
    private val schedulerProvider: SchedulerProvider,
    private val compositeDisposable: CompositeDisposable
) : BasePagingDataSource<DiscoverMovie>(false, true) {

    override fun loadInitialFromSource(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DiscoverMovie>
    ) {
        val disposable = discoverApi.getMovieByGenre(ApiConstants.STARTED_PAGE, genreId)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(
                { handleLoadInitialSuccess(params, callback, it.results) },
                { handleLoadInitialError(params, callback, it) }
            )
        compositeDisposable.add(disposable)
    }

    override fun loadAfterFromSource(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, DiscoverMovie>
    ) {
        val disposable = discoverApi.getMovieByGenre(params.key, genreId)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(
                { handleLoadAfterSuccess(params, callback, it.results, it.totalPage) },
                { handleLoadAfterError(params, callback, it) }
            )
        compositeDisposable.add(disposable)
    }

    override fun loadBeforeFromSource(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, DiscoverMovie>
    ) {
        // Ignore
    }

    class Factory(
        private val discoverApi: DiscoverApi,
        private val genreId: Int,
        private val schedulerProvider: SchedulerProvider,
        private val compositeDisposable: CompositeDisposable
    ) : DataSource.Factory<Int, DiscoverMovie>() {

        val sourceLiveData = MutableLiveData<GenreMovieDataSource>()

        override fun create(): DataSource<Int, DiscoverMovie> {
            val source =
                GenreMovieDataSource(discoverApi, genreId, schedulerProvider, compositeDisposable)
            sourceLiveData.postValue(source)
            return source
        }
    }
}
