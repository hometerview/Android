package com.ftw.domain.usecase.review

import com.ftw.domain.repository.review.ReviewRepository

class CreateReviewUseCaseImpl(
    private val repository: ReviewRepository
) : CreateReviewUseCase {
    override suspend fun invoke(
        buildingId: String,
        companyId: String,
        period: String,
        rating: Int,
        advantage: String,
        disadvantage: String,
        floor: String
    ) {
        repository.create(
            buildingId,
            companyId,
            period,
            rating,
            advantage,
            disadvantage,
            floor
        )
    }
}
