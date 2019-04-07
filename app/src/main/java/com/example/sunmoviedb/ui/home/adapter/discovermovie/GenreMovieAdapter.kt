package com.example.sunmoviedb.ui.home.adapter.discovermovie

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sunmoviedb.R
import com.example.sunmoviedb.data.DiscoverMovie
import com.example.sunmoviedb.ui.home.DiscoverMovieListener

/**
 * Created on 2019-04-21 by Sang
 * Description:
 **/
class GenreMovieAdapter(
    private val listener: DiscoverMovieListener
) : PagedListAdapter<DiscoverMovie, RecyclerView.ViewHolder>(DISCOVER_MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.item_big_genre_movie -> BigGenreMovieViewHolder.create(parent, listener)
            R.layout.item_small_genre_movie -> SmallGenreMovieViewHolder.create(parent, listener)
            else -> throw IllegalStateException("Unknown view type: $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_big_genre_movie -> {
                getItem(position)?.also { (holder as BigGenreMovieViewHolder).bindView(it) }
            }
            R.layout.item_small_genre_movie -> {
                getItem(position)?.also { (holder as SmallGenreMovieViewHolder).bindView(it) }
            }
            else -> throw IllegalStateException("Unknown view type: ${getItemViewType(position)}")
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (position % 5 == 0) {
            R.layout.item_big_genre_movie
        } else {
            R.layout.item_small_genre_movie
        }

    companion object {

        val DISCOVER_MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<DiscoverMovie>() {
            override fun areItemsTheSame(oldItem: DiscoverMovie, newItem: DiscoverMovie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: DiscoverMovie, newItem: DiscoverMovie
            ): Boolean = oldItem.posterPath == newItem.posterPath
        }
    }
}
