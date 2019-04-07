package com.example.sunmoviedb.ui.home.adapter.categorymovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sunmoviedb.databinding.ItemCategoryMovieRetryBinding
import com.example.sunmoviedb.ui.home.CategoryMovieListener

/**
 * Created on 2019-04-20 by Sang
 * Description:
 **/
class CategoryMovieRetryViewHolder(
    private val binding: ItemCategoryMovieRetryBinding,
    private val listener: CategoryMovieListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView() {
        binding.apply {
            this.listener = this@CategoryMovieRetryViewHolder.listener
            executePendingBindings()
        }
    }

    companion object {

        fun create(
            parent: ViewGroup, listener: CategoryMovieListener
        ): CategoryMovieRetryViewHolder {
            val binding = ItemCategoryMovieRetryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return CategoryMovieRetryViewHolder(binding, listener)
        }
    }
}
