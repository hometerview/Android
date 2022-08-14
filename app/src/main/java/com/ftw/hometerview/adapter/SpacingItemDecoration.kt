package com.ftw.hometerview.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import com.ftw.hometerview.R

class SpacingItemDecoration(
    context: Context,
    @DimenRes private val spacingResId: Int = R.dimen.dp_size_16
) : RecyclerView.ItemDecoration() {

    private val dividerHeight: Int = context.resources.getDimensionPixelSize(spacingResId)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(0, 0, 0, dividerHeight)

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = dividerHeight
        }
    }
}
