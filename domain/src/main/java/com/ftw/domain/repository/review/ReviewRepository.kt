package com.ftw.domain.repository.review

interface ReviewRepository {
    suspend fun create(
        buildingId: String,
        companyId: String,
        period: String,
        rating: Int,
        advantage: String,
        disadvantage: String,
        floor: String
    )
}
