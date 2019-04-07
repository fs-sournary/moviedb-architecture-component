package com.example.sunmoviedb.repository.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.sunmoviedb.api.ApiConstants
import com.example.sunmoviedb.api.MovieApi
import com.example.sunmoviedb.data.CategoryMovie
import com.example.sunmoviedb.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created on 4/7/19 by Sang
 * Description:
 **/
class UpComingCategoryMovieDataSource(
    private val movieApi: MovieApi,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BaseCategoryMovieDataSource(schedulerProvider, compositeDisposable) {

    override fun loadInitialFromSource(
        params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, CategoryMovie>
    ) {
        loadInitialData(params, callback, movieApi.getUpcomingMovies(ApiConstants.STARTED_PAGE))
    }

    override fun loadAfterFromSource(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CategoryMovie>
    ) {
        loadAfterData(params, callback, movieApi.getUpcomingMovies(params.key))
    }

    class Factory(
        private val movieApi: MovieApi,
        private val schedulerProvider: SchedulerProvider,
        private val compositeDisposable: CompositeDisposable
    ) : DataSource.Factory<Int, CategoryMovie>() {

        val sourceLiveData = MutableLiveData<UpComingCategoryMovieDataSource>()

        override fun create(): DataSource<Int, CategoryMovie> {
            val source =
                UpComingCategoryMovieDataSource(movieApi, schedulerProvider, compositeDisposable)
            sourceLiveData.postValue(source)
            return source
        }
    }
}