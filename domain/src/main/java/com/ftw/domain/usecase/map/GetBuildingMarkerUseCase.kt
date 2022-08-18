package com.ftw.domain.usecase.map

import com.ftw.domain.entity.BuildingMarker

interface GetBuildingMarkerUseCase {
    suspend operator fun invoke(location: String): List<BuildingMarker>
}
