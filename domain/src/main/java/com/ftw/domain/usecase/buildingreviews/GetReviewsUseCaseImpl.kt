package com.ftw.domain.usecase.buildingreviews

import com.ftw.domain.entity.TestReview
import com.ftw.domain.repository.buildingreviews.ReviewsRepository

class GetReviewsUseCaseImpl(private val reviewsRepository: ReviewsRepository) : GetReviewsUseCase {
    override suspend fun invoke(buildingId: String): List<TestReview> {
        return reviewsRepository.get(buildingId)
    }
}
