package com.ftw.domain.usecase.buildingreviews

import com.ftw.domain.entity.TestReview

interface GetReviewsUseCase {
    suspend operator fun invoke(buildingId: String): List<TestReview>
}
