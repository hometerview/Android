package com.ftw.data.remote.response.reviews

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewContents(
    @SerializedName("advantage")
    val advantage: String,
    @SerializedName("bookmarkCount")
    val bookmarkCount: Int,
    @SerializedName("buildingId")
    val buildingId: String,
    @SerializedName("certification")
    val certification: List<String>,
    @SerializedName("companyId")
    val companyId: String,
    @SerializedName("disadvantage")
    val disadvantage: String,
    @SerializedName("id")
    val id: Id,
    @SerializedName("likeCount")
    val likeCount: Int,
    @SerializedName("memberId")
    val memberId: String,
    @SerializedName("period")
    val period: String,
    @SerializedName("price")
    val price: Price,
    @SerializedName("rating")
    val rating: Int
) : Parcelable
