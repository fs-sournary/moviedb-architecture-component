package com.example.sunmoviedb.ui.home.adapter.categorymovie

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sunmoviedb.R
import com.example.sunmoviedb.data.CategoryMovie
import com.example.sunmoviedb.repository.NetworkState
import com.example.sunmoviedb.ui.home.CategoryMovieListener

/**
 * Create on 2019-04-20 by Sang
 * Description:
 **/
class CategoryMovieAdapter(private val listener: CategoryMovieListener) :
    PagedListAdapter<CategoryMovie, RecyclerView.ViewHolder>(CATEGORY_COMPARATOR) {

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.item_category_movie -> CategoryMovieViewHolder.create(parent, listener)
            R.layout.item_category_movie_retry -> {
                CategoryMovieRetryViewHolder.create(parent, listener)
            }
            else -> throw IllegalStateException("Unknown view type: $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_category_movie -> {
                getItem(position)?.also { (holder as CategoryMovieViewHolder).bindView(it) }
            }
            R.layout.item_category_movie_retry -> (holder as CategoryMovieRetryViewHolder).bindView()
            else -> throw IllegalStateException("Unknown view type: ${getItemViewType(position)}")
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_category_movie_retry
        } else {
            R.layout.item_category_movie
        }

    override fun getItemCount(): Int = super.getItemCount() + if (hasExtraRow()) 1 else 0

    private fun hasExtraRow(): Boolean =
        networkState != null && networkState != NetworkState.SUCCESS && networkState != NetworkState.RUNNING

    fun setNetworkState(newNetworkState: NetworkState) {
        val previousNetworkState = networkState
        val hadExtraRow = hasExtraRow()
        networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) { // Previous no internet but now has
                notifyItemRemoved(super.getItemCount())
            } else { // Previous has internet but now no
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousNetworkState != newNetworkState) {
            // No internet and network state change
            notifyItemChanged(itemCount - 1)
        }
    }

    companion object {

        val CATEGORY_COMPARATOR = object : DiffUtil.ItemCallback<CategoryMovie>() {
            override fun areItemsTheSame(oldItem: CategoryMovie, newItem: CategoryMovie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CategoryMovie, newItem: CategoryMovie
            ): Boolean =
                oldItem.title == newItem.title && oldItem.voteAverage == newItem.voteAverage && oldItem.posterPath == newItem.posterPath
        }
    }
}
