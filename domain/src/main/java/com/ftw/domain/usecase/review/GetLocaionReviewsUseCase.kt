package com.ftw.domain.usecase.review

import com.ftw.domain.entity.LocationReview

interface GetLocaionReviewsUseCase {
    operator fun invoke(location: String): List<LocationReview>
}
