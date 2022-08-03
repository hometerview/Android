package com.ftw.hometerview.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.ftw.hometerview.R

class DividerItemDecoration(
    context: Context,
    @ColorInt private val colorResId: Int = com.ftw.hometerview.design.R.color.gray_200
) : RecyclerView.ItemDecoration() {

    private val dividerHeight: Int = context.resources.getDimensionPixelSize(R.dimen.dp_size_1)
    private val horizontalMargin: Int = context.resources.getDimensionPixelSize(R.dimen.dp_size_14)

    private val paint = Paint().apply {
        this.color = ContextCompat.getColor(context, colorResId)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(0, 0, 0, dividerHeight)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        c.save()
        parent.children
            .forEach { children ->
                c.drawRect(
                    Rect(
                        children.left + horizontalMargin,
                        children.bottom + dividerHeight,
                        children.right - horizontalMargin,
                        children.bottom
                    ),
                    paint
                )
            }
        c.restore()
    }
}
