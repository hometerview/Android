package com.ftw.data.repository.review

import com.ftw.data.datasource.BuildingReviewsDataSource
import com.ftw.domain.entity.TestReview
import com.ftw.domain.repository.buildingreviews.ReviewsRepository

class ReviewsRepositoryImpl(private val buildingReviewsDataSource: BuildingReviewsDataSource) : ReviewsRepository {
    override suspend fun get(buildingId: String): List<TestReview> {
        return buildingReviewsDataSource.get(buildingId)
    }
}
