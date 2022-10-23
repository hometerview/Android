package com.ftw.domain.usecase.review

interface CreateReviewUseCase {
    suspend operator fun invoke(
        buildingId: String,
        companyId: String,
        period: String,
        rating: Int,
        advantage: String,
        disadvantage: String,
        floor: String
    )
}
