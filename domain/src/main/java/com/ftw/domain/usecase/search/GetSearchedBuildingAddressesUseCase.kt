package com.ftw.domain.usecase.search

import com.ftw.domain.entity.Address

interface GetSearchedBuildingAddressesUseCase {
    suspend operator fun invoke(keyword: String): List<Address>
}
