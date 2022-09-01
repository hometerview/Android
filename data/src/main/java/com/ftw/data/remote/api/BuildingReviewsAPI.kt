package com.ftw.data.remote.api

import com.ftw.data.remote.response.Response
import com.ftw.data.remote.response.reviews.Review
import retrofit2.http.GET
import retrofit2.http.Query

interface BuildingReviewsAPI {
    @GET("/api/v1/review/detail")
    suspend fun get(@Query("buildingId") buildingId: String): Response<List<Review>>
}
