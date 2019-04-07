package com.example.sunmoviedb.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.sunmoviedb.R

/**
 * Created on 2019-04-20 by Sang
 * Description:
 **/
class GenreMovieItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val resource = parent.context.resources
        val position = parent.getChildAdapterPosition(view)
        if (position == 0) {
            outRect.left = resource.getDimensionPixelOffset(R.dimen.dp_16)
        } else {
            outRect.left = resource.getDimensionPixelOffset(R.dimen.dp_5)
        }
        outRect.right = resource.getDimensionPixelOffset(R.dimen.dp_5)
        outRect.top = resource.getDimensionPixelOffset(R.dimen.dp_5)
        outRect.bottom = resource.getDimensionPixelOffset(R.dimen.dp_5)
    }
}
