package com.ftw.data.remote.response.review

import com.google.gson.annotations.SerializedName

data class PriceResponse(
    @SerializedName("deposit")
    val deposit: String,
    @SerializedName("maintainFee")
    val maintainFee: String,
    @SerializedName("monthly")
    val monthly: String
)
