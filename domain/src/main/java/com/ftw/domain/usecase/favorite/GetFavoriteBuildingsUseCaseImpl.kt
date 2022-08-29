package com.ftw.domain.usecase.favorite

import com.ftw.domain.entity.*

class GetFavoriteBuildingsUseCaseImpl : GetFavoriteBuildingsUseCase {
    override suspend fun invoke(): List<FavoriteBuilding> {
        return listOf(
            FavoriteBuilding(
                buildingId = 0,
                buildingName = "아크로텔 오피스텔",
                type = BuildingType.OFFICETEL,
                address = Address(0, 0, "인천 남동구 구월남로 125", ""),
                reviewCount = 2,
                rating = 4.0,
                favorite = true
            ),
            FavoriteBuilding(
                buildingId = 0,
                buildingName = "한국 아파트",
                type = BuildingType.OFFICETEL,
                address = Address(0, 0, "성남대로 1226번길 2", ""),
                reviewCount = 4,
                rating = 4.0,
                favorite = true
            ),
            FavoriteBuilding(
                buildingId = 0,
                buildingName = "한빛 삼성 아파트",
                type = BuildingType.OFFICETEL,
                address = Address(0, 0, "서재2길 41-9", ""),
                reviewCount = 6,
                rating = 3.9,
                favorite = true
            )
        )
    }

}
