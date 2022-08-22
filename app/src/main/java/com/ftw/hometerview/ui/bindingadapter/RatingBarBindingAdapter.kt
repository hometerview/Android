package com.ftw.hometerview.ui.bindingadapter

import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

@BindingAdapter("android:rating")
fun setRating(ratingBar: RatingBar, rating: Float) {
    ratingBar.rating = rating
}

@InverseBindingAdapter(attribute = "android:rating", event = "ratingBarRatingChanged")
fun getRating(ratingBar: RatingBar): Float {
    return ratingBar.rating
}

@BindingAdapter("ratingBarRatingChanged")
fun setRatingChanged(ratingBar: RatingBar, attrChanged: InverseBindingListener) {
    ratingBar.setOnRatingBarChangeListener { _, _, _ ->
        attrChanged.onChange()
    }
}
