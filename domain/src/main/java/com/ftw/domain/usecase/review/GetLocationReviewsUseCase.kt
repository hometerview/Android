package com.ftw.domain.usecase.review

import com.ftw.domain.entity.LocationReview

interface GetLocationReviewsUseCase {
    suspend operator fun invoke(location: String): List<LocationReview>
}
