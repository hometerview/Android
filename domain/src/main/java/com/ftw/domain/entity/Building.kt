package com.ftw.domain.entity

data class Building(
    val id: Long,
    val name: String,
    val type: BuildingType,
    val buildingAddress: BuildingAddress,
    val salePrice: Price,
    val charterPrice: Price,
    val rating: Int,
    val favorite: Boolean
) {
    companion object {
        val NONE = Building(
            id = 0,
            name = "",
            type = BuildingType.OFFICETEL,
            buildingAddress = BuildingAddress(0, 0, "", ""),
            salePrice = Price("", "", ""),
            charterPrice = Price("", "", ""),
            rating = 0,
            favorite = false
        )
    }
}
