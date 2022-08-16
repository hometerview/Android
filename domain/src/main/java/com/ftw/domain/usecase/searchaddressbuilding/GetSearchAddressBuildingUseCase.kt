package com.ftw.domain.usecase.searchaddressbuilding

import com.ftw.domain.entity.SearchAddressBuildingResult

interface GetSearchAddressBuildingUseCase {
    suspend operator fun invoke(location: String): List<SearchAddressBuildingResult>
}
