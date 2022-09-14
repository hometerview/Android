package com.ftw.data.datasource

import com.ftw.domain.entity.TestReview

interface BuildingReviewsDataSource {
    suspend fun get(buildingId: String): List<TestReview>
}
