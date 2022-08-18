package com.ftw.domain.usecase.map

import com.ftw.domain.entity.StationMarker

interface GetMarkerUseCase {
    suspend operator fun invoke(location: String): List<StationMarker>
}
