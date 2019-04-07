package com.example.sunmoviedb.di

import com.example.sunmoviedb.data.CategoryMovie
import com.example.sunmoviedb.data.DiscoverMovie
import com.example.sunmoviedb.ui.account.AccountViewModel
import com.example.sunmoviedb.ui.captionsetting.CaptionSettingViewModel
import com.example.sunmoviedb.ui.globalsetting.GlobalSettingViewModel
import com.example.sunmoviedb.ui.home.HomeViewModel
import com.example.sunmoviedb.ui.library.LibraryViewModel
import com.example.sunmoviedb.ui.moviedetail.MovieDetailViewModel
import com.example.sunmoviedb.ui.movieoption.MovieOptionViewModel
import com.example.sunmoviedb.ui.watchedlist.WatchedListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created on 4/5/19 by Sang
 * Description:
 **/
val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { GlobalSettingViewModel() }
    viewModel { LibraryViewModel() }
    viewModel { WatchedListViewModel() }
    viewModel { CaptionSettingViewModel() }
    viewModel { AccountViewModel() }
    viewModel { (movie: CategoryMovie?, discoverMovie: DiscoverMovie?) ->
        MovieOptionViewModel(movie, discoverMovie)
    }
    viewModel { (movieId: Int) -> MovieDetailViewModel(movieId, get()) }
}
