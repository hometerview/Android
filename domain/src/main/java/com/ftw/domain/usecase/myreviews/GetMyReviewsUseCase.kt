package com.ftw.domain.usecase.myreviews

import com.ftw.domain.entity.WrittenReview

interface GetMyReviewsUseCase {
    suspend operator fun invoke(): List<WrittenReview>
}
