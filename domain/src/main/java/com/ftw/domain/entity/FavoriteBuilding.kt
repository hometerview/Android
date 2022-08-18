package com.ftw.domain.entity

data class FavoriteBuilding(
    val buildingId: Long,
    val buildingName: String,
    val type: BuildingType,
    val address: Address,
    val reviewCount: Int,
    val rating: Double,
    val favorite: Boolean
)
