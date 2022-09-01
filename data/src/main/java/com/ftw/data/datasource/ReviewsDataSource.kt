package com.ftw.data.datasource

import com.ftw.data.remote.response.Response
import com.ftw.data.remote.response.reviews.Review

interface ReviewsDataSource {
    suspend fun get(buildingId: String): Response<List<Review>>
}
