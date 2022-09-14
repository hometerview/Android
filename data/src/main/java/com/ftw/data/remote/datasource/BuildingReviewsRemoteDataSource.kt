package com.ftw.data.remote.datasource

import com.ftw.data.datasource.BuildingReviewsDataSource
import com.ftw.data.remote.api.BuildingReviewsAPI
import com.ftw.data.remote.exception.ResponseException
import com.ftw.data.remote.response.review.toTestReview
import com.ftw.domain.entity.TestReview

class BuildingReviewsRemoteDataSource(private val api: BuildingReviewsAPI) :
    BuildingReviewsDataSource {

    override suspend fun get(buildingId: String): List<TestReview> {
        return try {
            val response = api.get(buildingId)
            if (response.isSuccessful) {
                response.body()?.data?.map { it.toTestReview() } ?: emptyList()
            } else {
                throw ResponseException("Network Exception")
            }
        } catch (e: Exception) {
            throw e
        }
    }
}
