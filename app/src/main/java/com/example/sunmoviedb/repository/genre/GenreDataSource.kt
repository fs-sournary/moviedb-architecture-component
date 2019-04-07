package com.example.sunmoviedb.repository.genre

import androidx.lifecycle.MutableLiveData
import com.example.sunmoviedb.api.GenreApi
import com.example.sunmoviedb.data.Genre
import com.example.sunmoviedb.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created on 2019-04-20 by Sang
 * Description:
 **/
class GenreDataSource(
    private val genreApi: GenreApi,
    private val schedulerProvider: SchedulerProvider,
    private val compositeDisposable: CompositeDisposable
) {

    val genres = MutableLiveData<List<Genre>>()

    fun getGenres() {
        val disposable = genreApi.getGenres()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(
                { genres.value = it.genres },
                { }
            )
        compositeDisposable.add(disposable)
    }
}
