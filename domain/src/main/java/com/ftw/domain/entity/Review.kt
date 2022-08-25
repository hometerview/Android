package com.ftw.domain.entity

import java.util.Date

data class Review(
    val buildingId: Long,
    val buildingName: String,
    val buildingType: BuildingType,
    val buildingAddress: String,
    val rating: Int,
    val summary: String,
    val residentialPeriod: String,
    val residentialFloor: String,
    val officeLocation: String,
    val advantage: String,
    val disadvantage: String,
    val favorite: Boolean,
    val leftAt: Date
)
