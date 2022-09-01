package com.ftw.data.remote.response.review

import com.google.gson.annotations.SerializedName

data class ReviewResponse(
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
    val id: IdResponse,
    @SerializedName("likeCount")
    val likeCount: Int,
    @SerializedName("memberId")
    val memberId: String,
    @SerializedName("period")
    val period: String,
    @SerializedName("price")
    val priceResponse: PriceResponse,
    @SerializedName("rating")
    val rating: Int
)
