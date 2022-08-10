package com.ftw.domain.usecase.map

import com.ftw.domain.entity.BuildingMarker

class GetBuildingMarkerUseCaseImpl : GetBuildingMarkerUseCase {
    override suspend fun invoke(location: String): List<BuildingMarker> {
        return listOf(
            BuildingMarker(
                buildingName = "죽암 빌딩",
                latitude = 37.50236738491151,
                longitude = 127.03654606433355,
                buildingId = 1
            ),
            BuildingMarker(
                buildingName = "동복 빌딩",
                latitude = 37.501057286645356,
                longitude = 127.03363332712635,
                buildingId = 2
            ),
            BuildingMarker(
                buildingName = "정인 빌딩",
                latitude = 37.50065429139799,
                longitude = 127.04018674675399,
                buildingId = 3
            ),
            BuildingMarker(
                buildingName = "창성 빌딩",
                latitude = 37.50095326004147,
                longitude = 127.03504691863799,
                buildingId = 4
            ),
            BuildingMarker(
                buildingName = "진성 빌딩",
                latitude = 37.49987639741427,
                longitude = 127.03558359012032,
                buildingId = 5
            ),
            BuildingMarker(
                buildingName = "근도 빌딩",
                latitude = 37.49864101548692,
                longitude = 127.03877207326005,
                buildingId = 6
            ),
            BuildingMarker(
                buildingName = "화원 빌딩",
                latitude = 37.49728970863099,
                longitude = 127.03814940421368,
                buildingId = 7
            )
        )
    }

}
