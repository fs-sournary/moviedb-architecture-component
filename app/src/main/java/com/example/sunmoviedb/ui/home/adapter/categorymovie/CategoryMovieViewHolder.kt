package com.example.sunmoviedb.ui.home.adapter.categorymovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sunmoviedb.data.CategoryMovie
import com.example.sunmoviedb.databinding.ItemCategoryMovieBinding
import com.example.sunmoviedb.ui.home.CategoryMovieListener

/**
 * Created on 2019-04-20 by Sang
 * Description:
 **/
class CategoryMovieViewHolder(
    private val binding: ItemCategoryMovieBinding, private val listener: CategoryMovieListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(movie: CategoryMovie) {
        ViewCompat.setTransitionName(binding.thumbnailImage, movie.id.toString())
        binding.apply {
            item = movie
            position = adapterPosition
            listener = this@CategoryMovieViewHolder.listener
            executePendingBindings()
        }
    }

    companion object {

        fun create(parent: ViewGroup, listener: CategoryMovieListener): CategoryMovieViewHolder {
            val binding =
                ItemCategoryMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CategoryMovieViewHolder(binding, listener)
        }
    }
}
