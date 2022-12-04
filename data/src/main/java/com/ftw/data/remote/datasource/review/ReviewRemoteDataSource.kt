package com.ftw.data.remote.datasource.review

import com.ftw.data.datasource.review.ReviewDataSource
import com.ftw.data.remote.api.review.ReviewAPI
import com.ftw.data.remote.exception.ResponseException
import com.ftw.data.remote.request.CreateReviewRequest

class ReviewRemoteDataSource(
    private val api: ReviewAPI
) : ReviewDataSource {
    override suspend fun create(
        buildingId: String,
        companyId: String,
        period: String,
        rating: Int,
        advantage: String,
        disadvantage: String,
        floor: String
    ) {
        try {
            val response = api.create(
                CreateReviewRequest(
                    buildingId,
                    companyId,
                    period,
                    rating,
                    advantage,
                    disadvantage,
                    floor
                )
            )

            if (!response.isSuccessful) {
                throw ResponseException()
            }
        } catch (e: Exception) {
            throw e
        }
    }
}
