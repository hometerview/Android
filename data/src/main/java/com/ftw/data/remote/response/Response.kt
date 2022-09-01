package com.ftw.data.remote.response

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("data")
    val data: T?,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("responseCode")
    val responseCode: String? = null
)
