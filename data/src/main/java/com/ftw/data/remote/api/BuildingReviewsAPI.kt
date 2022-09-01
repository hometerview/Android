package com.ftw.data.remote.api

import com.ftw.data.remote.response.RemoteResponse
import com.ftw.data.remote.response.review.BuildingReviewResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BuildingReviewsAPI {
    @GET("/api/v1/review/detail")
    suspend fun get(@Query("buildingId") buildingId: String): Response<RemoteResponse<List<BuildingReviewResponse>>>
}
