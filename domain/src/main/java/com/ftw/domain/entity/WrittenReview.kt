package com.ftw.domain.entity

import java.util.Date

data class WrittenReview(
    val buildingId: Long,
    val buildingName: String,
    val rating: Int,
    val summary: String,
    val residentialPeriod: String,
    val residentialFloor: String,
    val officeLocation: String,
    val savedAdvantage: String,
    val savedDisadvantage: String,
    val favorite: Boolean,
    val leftAt: Date
)
