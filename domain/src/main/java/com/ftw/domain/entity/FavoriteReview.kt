package com.ftw.domain.entity

import java.util.Date

data class FavoriteReview(
    val buildingId: Long,
    val buildingName: String,
    val rating: Int,
    val summary: String,
    val officeLocation: String,
    val savedAdvantage: String,
    val savedDisadvantage: String,
    val favorite: Boolean,
    val leftAt: Date
)
