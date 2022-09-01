package com.ftw.data.network.datasource

import com.ftw.data.datasource.ReviewsDataSource
import com.ftw.data.remote.api.BuildingReviewsAPI
import com.ftw.data.remote.response.Response
import com.ftw.data.remote.response.reviews.Review

class ReviewsRemoteDataSource(private val api: BuildingReviewsAPI) : ReviewsDataSource {
    override suspend fun get(buildingId: String): Response<List<Review>> {
        return api.get(buildingId)
    }
}
