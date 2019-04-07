package com.example.sunmoviedb.ui.home

import android.view.View
import com.example.sunmoviedb.data.DiscoverMovie

/**
 * Created on 2019-04-21 by Sang
 * Description:
 **/
interface DiscoverMovieListener {

    fun onItemClicked(v: View, movieId: Int)

    fun onItemLongClicked(movie: DiscoverMovie): Boolean
}
