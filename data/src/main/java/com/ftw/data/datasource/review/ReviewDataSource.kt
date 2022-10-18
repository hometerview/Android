package com.ftw.data.datasource.review

interface ReviewDataSource {
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
