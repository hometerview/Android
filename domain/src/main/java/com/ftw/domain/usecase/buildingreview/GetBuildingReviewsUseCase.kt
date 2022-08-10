package com.ftw.domain.usecase.buildingreview

import com.ftw.domain.entity.BuildingReview

interface GetBuildingReviewsUseCase {
    suspend operator fun invoke(buildingId: Long): BuildingReview
}
