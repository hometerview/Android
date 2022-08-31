package com.ftw.domain.usecase.buildingreviews

import com.ftw.domain.entity.Review

interface GetReviewsUseCase {
    suspend operator fun invoke(buildingId: Long): List<Review>
}
