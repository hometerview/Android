package com.ftw.domain.usecase.favorite

import com.ftw.domain.entity.FavoriteReview

interface GetFavoriteReviewsUseCase {
    suspend operator fun invoke(): List<FavoriteReview>
}
