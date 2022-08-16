package com.ftw.domain.entity

data class BuildingMarker(
    val buildingName: String,
    val latitude: Double,
    val longitude: Double,
    val reviewCnt: Int,
    val buildingId: Int
)  {
    companion object {
        val NONE = BuildingMarker("", 0.0, 0.0, 0, 0)
    }
}
