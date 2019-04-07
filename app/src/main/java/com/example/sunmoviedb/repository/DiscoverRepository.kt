package com.example.sunmoviedb.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.sunmoviedb.api.ApiConstants
import com.example.sunmoviedb.api.DiscoverApi
import com.example.sunmoviedb.data.DiscoverMovie
import com.example.sunmoviedb.repository.discover.GenreMovieDataSource
import com.example.sunmoviedb.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Create on 2019-04-21 by Sang
 * Description:
 **/
class DiscoverRepository(
    private val discoverApi: DiscoverApi,
    private val schedulerProvider: SchedulerProvider,
    private val compositeDisposable: CompositeDisposable
) {

    fun getMovieByGenre(genreId: Int): RepoResult<LiveData<PagedList<DiscoverMovie>>> {
        val sourceFactory = GenreMovieDataSource.Factory(
            discoverApi, genreId, schedulerProvider, compositeDisposable
        )
        val livePagedList = sourceFactory.toLiveData(ApiConstants.PAGE_LOAD_SIZE)
        return RepoResult(data = livePagedList)
    }

    fun clear() {
        compositeDisposable.clear()
    }
}
