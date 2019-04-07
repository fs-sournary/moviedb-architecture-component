package com.example.sunmoviedb.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.sunmoviedb.R

/**
 * Created on 2019-04-20 by Sang
 * Description:
 **/
class CategoryMovieItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val resources = parent.context.resources
        outRect.left = if (parent.getChildAdapterPosition(view) == 0) {
            resources.getDimensionPixelOffset(R.dimen.dp_16)
        } else {
            resources.getDimensionPixelOffset(R.dimen.dp_5)
        }
        outRect.right = resources.getDimensionPixelOffset(R.dimen.dp_5)
    }
}
