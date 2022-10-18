package com.ftw.data.remote.api.review

import com.ftw.data.remote.request.CreateReviewRequest
import com.ftw.data.remote.response.RemoteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ReviewAPI {
    @POST("/api/v1/review")
    fun create(
        @Body review: CreateReviewRequest
    ): Response<RemoteResponse<Nothing>>
}
