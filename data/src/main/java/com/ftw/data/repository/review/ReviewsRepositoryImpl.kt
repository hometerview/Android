package com.ftw.data.repository.review

import com.ftw.data.datasource.ReviewsDataSource
import com.ftw.data.mapper.ReviewsMapper
import com.ftw.domain.entity.TestReview
import com.ftw.domain.repository.buildingreviews.ReviewsRepository

class ReviewsRepositoryImpl(private val reviewsDataSource: ReviewsDataSource) : ReviewsRepository {
    override suspend fun get(buildingId: String): List<TestReview>? {
        return reviewsDataSource.get(buildingId).data?.map {
            ReviewsMapper.mapperToReview(it)
        }
    }
}
