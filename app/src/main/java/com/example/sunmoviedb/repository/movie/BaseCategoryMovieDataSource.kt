package com.example.sunmoviedb.repository.movie

import com.example.sunmoviedb.data.CategoryMovie
import com.example.sunmoviedb.data.response.CategoryMovieResponse
import com.example.sunmoviedb.repository.BasePagingDataSource
import com.example.sunmoviedb.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

/**
 * Created on 4/7/19 by Sang
 * Description:
 **/
abstract class BaseCategoryMovieDataSource(
    private val schedulerProvider: SchedulerProvider,
    private val compositeDisposable: CompositeDisposable
) : BasePagingDataSource<CategoryMovie>(false, true) {

    override fun loadBeforeFromSource(
        params: LoadParams<Int>, callback: LoadCallback<Int, CategoryMovie>
    ) {
        // Ignore
    }

    fun loadInitialData(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CategoryMovie>,
        single: Single<CategoryMovieResponse>
    ) {
        val disposable = single.observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe(
                { handleLoadInitialSuccess(params, callback, it.results) },
                { handleLoadInitialError(params, callback, it) }
            )
        compositeDisposable.add(disposable)
    }

    fun loadAfterData(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CategoryMovie>,
        single: Single<CategoryMovieResponse>
    ) {
        val disposable = single.observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe(
                { handleLoadAfterSuccess(params, callback, it.results, it.totalPage) },
                { handleLoadAfterError(params, callback, it) }
            )
        compositeDisposable.add(disposable)
    }

    fun clear(){
        compositeDisposable.clear()
    }
}
