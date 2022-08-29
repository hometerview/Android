package com.ftw.domain.usecase.myreviews

import com.ftw.domain.entity.Review

interface GetMyReviewsUseCase {
    suspend operator fun invoke(): List<Review>
}
