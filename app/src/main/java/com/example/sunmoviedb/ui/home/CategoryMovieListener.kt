package com.example.sunmoviedb.ui.home

import android.view.View
import com.example.sunmoviedb.data.CategoryMovie

/**
 * Created on 2019-04-20 by Sang
 * Description:
 **/
interface CategoryMovieListener {

    fun retry()

    fun onItemClicked(v: View, movieId: Int)

    fun onItemLongClicked(movie: CategoryMovie): Boolean
}
