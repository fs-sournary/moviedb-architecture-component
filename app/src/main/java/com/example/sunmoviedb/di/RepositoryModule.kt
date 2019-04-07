package com.example.sunmoviedb.di

import com.example.sunmoviedb.repository.MovieRepository
import com.example.sunmoviedb.repository.DiscoverRepository
import com.example.sunmoviedb.repository.GenreRepository
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

/**
 * Created on 4/5/19 by Sang
 * Description:
 **/
val repositoryModule = module {
    single(StringQualifier("CategoryComposite")) { createCategoryCompositeDisposable() }
    single(StringQualifier("DiscoverComposite")) { createDiscoverCompositeDisposable() }
    single(StringQualifier("GenreComposite")) { createGenreCompositeDisposable() }
    single { MovieRepository(get(), get(), get(StringQualifier("CategoryComposite"))) }
    single { GenreRepository(get(), get(), get(StringQualifier("GenreComposite"))) }
    single { DiscoverRepository(get(), get(), get(StringQualifier("DiscoverComposite"))) }
}

fun createCategoryCompositeDisposable(): CompositeDisposable = CompositeDisposable()

fun createDiscoverCompositeDisposable(): CompositeDisposable = CompositeDisposable()

fun createGenreCompositeDisposable(): CompositeDisposable = CompositeDisposable()
