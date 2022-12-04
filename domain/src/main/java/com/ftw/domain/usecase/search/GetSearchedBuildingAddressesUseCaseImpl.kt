package com.ftw.domain.usecase.search

import com.ftw.domain.entity.Address
import com.ftw.domain.repository.search.SearchRepository

class GetSearchedBuildingAddressesUseCaseImpl(
    private val repository: SearchRepository
) : GetSearchedBuildingAddressesUseCase {
    override suspend fun invoke(keyword: String): List<Address> {
        return repository.buildings(keyword)
//        return listOf(
//            Address("0", "서울 마포구 월드컵북로"),
//            Address("1", "서울 마포구 월드컵북로 1길 35"),
//            Address("2", "서울 마포구 월드컵북로 2길 3"),
//            Address("3", "서울 마포구 월드컵북로 4길 6"),
//            Address("4", "서울 마포구 월드컵북로 5가길 12"),
//            Address("5", "서울 마포구 월드컵북로 5나길 2"),
//            Address("6", "서울 마포구 월드컵북로 52길 9")
//        )
    }
}
