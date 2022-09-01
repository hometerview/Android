package com.ftw.data.remote.response.reviews

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review(
    @SerializedName("isBookmarked")
    val isBookmarked: Boolean?,
    @SerializedName("isLiked")
    val isLiked: Boolean?,
    @SerializedName("review")
    val review: ReviewContents
) : Parcelable
