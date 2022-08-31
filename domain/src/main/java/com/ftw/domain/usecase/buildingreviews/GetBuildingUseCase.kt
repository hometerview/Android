package com.ftw.domain.usecase.buildingreviews

import com.ftw.domain.entity.Building

interface GetBuildingUseCase {
    suspend operator fun invoke(buildingId: Long): Building
}
