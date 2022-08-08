package com.ftw.domain.entity

import java.util.Date

data class Review(
    val buildingName: String,
    val buildingType: BuildingType,
    val address: String,
    val rating: Int,
    val officeLocation: String,
    val advantage: String,
    val disadvantage: String,
    val favorite: Boolean,
    val leftAt: Date
)
