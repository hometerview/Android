package com.ftw.domain.usecase.writtenreview

import com.ftw.domain.entity.WrittenReview

interface GetWrittenReviewsUseCase {
    suspend operator fun invoke(): List<WrittenReview>
}
