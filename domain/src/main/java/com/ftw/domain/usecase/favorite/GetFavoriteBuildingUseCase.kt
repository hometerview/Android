package com.ftw.domain.usecase.favorite

import com.ftw.domain.entity.FavoriteBuilding

interface GetFavoriteBuildingUseCase {
    suspend operator fun invoke(): List<FavoriteBuilding>
}
