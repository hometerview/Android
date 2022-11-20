package com.ftw.data.remote.api

import com.ftw.data.remote.request.KakaoTokenReq
import com.ftw.data.remote.response.RemoteResponse
import retrofit2.Response
import retrofit2.http.*

interface SignUpAPI {

    @POST("/api/v1/auth/login/kakao")
    suspend fun create(
        @Body kakaoTokenReq: KakaoTokenReq
        ): Response<RemoteResponse<Nothing>>
}
