package com.example.sunmoviedb.ui.home.adapter.discovermovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sunmoviedb.data.DiscoverMovie
import com.example.sunmoviedb.databinding.ItemSmallGenreMovieBinding
import com.example.sunmoviedb.ui.home.DiscoverMovieListener

/**
 * Created on 2019-04-21 by Sang
 * Description:
 **/
class SmallGenreMovieViewHolder(
    private val binding: ItemSmallGenreMovieBinding,
    private val listener: DiscoverMovieListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(movie: DiscoverMovie) {
        ViewCompat.setTransitionName(binding.thumbnailImage, movie.id.toString())
        binding.apply {
            item = movie
            listener = this@SmallGenreMovieViewHolder.listener
            executePendingBindings()
        }
    }

    companion object {

        fun create(parent: ViewGroup, listener: DiscoverMovieListener): SmallGenreMovieViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemSmallGenreMovieBinding.inflate(layoutInflater, parent, false)
            return SmallGenreMovieViewHolder(
                binding,
                listener
            )
        }
    }
}
