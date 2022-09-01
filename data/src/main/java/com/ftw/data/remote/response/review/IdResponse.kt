package com.ftw.data.remote.response.review

import com.google.gson.annotations.SerializedName

data class IdResponse(
    @SerializedName("date")
    val date: String,
    @SerializedName("timestamp")
    val timestamp: Int
)
