package com.anniekobia.harrypotter.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SpacesItemDecoration(private val space: Int) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildLayoutPosition(view) % 2 == 1) {
            outRect.right = 0
            outRect.left = space
        } else {
            outRect.right = space
            outRect.left = 0
        }
    }
}