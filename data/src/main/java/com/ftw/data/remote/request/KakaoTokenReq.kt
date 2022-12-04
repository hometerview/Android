package com.ftw.data.remote.request

import com.google.gson.annotations.SerializedName

data class KakaoTokenReq(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String
)