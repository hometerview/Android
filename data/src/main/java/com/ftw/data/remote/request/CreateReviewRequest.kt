package com.ftw.data.remote.request

data class CreateReviewRequest(
    val buildingId: String,
    val companyId: String,
    val period: String,
    val rating: Int,
    val advantage: String,
    val disadvantage: String,
    val floor: String
)
