package com.example.sunmoviedb.repository.movie

import androidx.lifecycle.MutableLiveData
import com.example.sunmoviedb.api.MovieApi
import com.example.sunmoviedb.data.DetailMovie
import com.example.sunmoviedb.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created on 2019-04-25 by Sang
 * Description:
 **/
class DetailMovieDataSource(
    private val movieApi: MovieApi,
    private val schedulerProvider: SchedulerProvider,
    private val compositeDisposable: CompositeDisposable
) {

    val movie = MutableLiveData<DetailMovie>()

    fun getMovie(movieId: Int) {
        val disposable = movieApi.getDetailMovie(movieId)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(
                { movie.value = it },
                {}
            )
        compositeDisposable.add(disposable)
    }
}
