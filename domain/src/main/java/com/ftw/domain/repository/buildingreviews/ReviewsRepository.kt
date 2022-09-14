package com.ftw.domain.repository.buildingreviews

import com.ftw.domain.entity.TestReview

interface ReviewsRepository {
    suspend fun get(buildingId: String): List<TestReview>
}
