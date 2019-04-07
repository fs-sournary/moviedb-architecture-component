package com.example.sunmoviedb.ui.home.adapter.discovermovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sunmoviedb.data.DiscoverMovie
import com.example.sunmoviedb.databinding.ItemBigGenreMovieBinding
import com.example.sunmoviedb.ui.home.DiscoverMovieListener

/**
 * Created on 2019-04-21 by Sang
 * Description:
 **/
class BigGenreMovieViewHolder(
    private val binding: ItemBigGenreMovieBinding,
    private val listener: DiscoverMovieListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(movie: DiscoverMovie) {
        ViewCompat.setTransitionName(binding.thumbnailImage, movie.id.toString())
        binding.apply {
            item = movie
            listener = this@BigGenreMovieViewHolder.listener
            executePendingBindings()
        }
    }

    companion object {

        fun create(parent: ViewGroup, listener: DiscoverMovieListener): BigGenreMovieViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemBigGenreMovieBinding.inflate(layoutInflater, parent, false)
            return BigGenreMovieViewHolder(
                binding,
                listener
            )
        }
    }
}
