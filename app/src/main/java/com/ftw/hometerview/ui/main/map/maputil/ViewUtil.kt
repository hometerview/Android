package com.ftw.hometerview.ui.main.map.maputil

import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.DisplayMetrics
import android.view.View

// View를 Bitmap으로 변환
fun View.createDrawable(): Bitmap {
    this.apply {

        measure(DisplayMetrics().widthPixels, DisplayMetrics().heightPixels)
        layout(0, 0, DisplayMetrics().widthPixels, DisplayMetrics().heightPixels)

        val bitmap: Bitmap = Bitmap.createBitmap(
            this.measuredWidth,
            this.measuredHeight,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        draw(canvas)

        return bitmap
    }
}