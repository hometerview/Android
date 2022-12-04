package com.ftw.data.repository.review

import com.ftw.data.datasource.review.ReviewDataSource
import com.ftw.domain.repository.review.ReviewRepository

class ReviewRepositoryImpl(
    private val dataSource: ReviewDataSource
) : ReviewRepository {
    override suspend fun create(
        buildingId: String,
        companyId: String,
        period: String,
        rating: Int,
        advantage: String,
        disadvantage: String,
        floor: String
    ) {
        dataSource.create(buildingId, companyId, period, rating, advantage, disadvantage, floor)
    }
}
