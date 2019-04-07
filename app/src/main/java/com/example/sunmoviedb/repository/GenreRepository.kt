package com.example.sunmoviedb.repository

import androidx.lifecycle.LiveData
import com.example.sunmoviedb.api.GenreApi
import com.example.sunmoviedb.data.Genre
import com.example.sunmoviedb.repository.genre.GenreDataSource
import com.example.sunmoviedb.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created on 2019-04-20 by Sang
 * Description:
 **/
class GenreRepository(
    private val genreApi: GenreApi,
    private val schedulerProvider: SchedulerProvider,
    private val compositeDisposable: CompositeDisposable
) {

    fun getGenre(): RepoResult<LiveData<List<Genre>>> {
        val dataSource = GenreDataSource(genreApi, schedulerProvider, compositeDisposable)
        dataSource.getGenres()
        return RepoResult(data = dataSource.genres)
    }

    fun clear(){
        compositeDisposable.clear()
    }
}
