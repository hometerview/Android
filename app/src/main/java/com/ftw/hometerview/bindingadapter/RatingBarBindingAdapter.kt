package com.ftw.hometerview.bindingadapter

import android.widget.RatingBar
import androidx.databinding.BindingAdapter

@BindingAdapter("android:rating")
fun setRating(ratingBar: RatingBar, rating: Float) {
    ratingBar.rating = rating
}
