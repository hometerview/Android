package com.ftw.domain.entity

data class StationMarker(
    val station: String,
    val latitude: Double,
    val longitude: Double,
    val buildingCnt: Int
) {
    companion object {
        val NONE = StationMarker("", 0.0, 0.0, 0)
    }
}
