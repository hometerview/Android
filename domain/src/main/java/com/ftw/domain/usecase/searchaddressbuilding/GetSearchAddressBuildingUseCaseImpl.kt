package com.ftw.domain.usecase.searchaddressbuilding

import com.ftw.domain.entity.SearchAddressBuildingResult

class GetSearchAddressBuildingUseCaseImpl : GetSearchAddressBuildingUseCase {
    override suspend fun invoke(location: String): List<SearchAddressBuildingResult> {
        return listOf(
            SearchAddressBuildingResult(
                result = "서울시 마포구",
                address = "서울시 마포구 흥정로 32, 34",
                type = "지역"
            ),
            SearchAddressBuildingResult(
                result = "마포역",
                address = "서울시 관악구 무슨로 34, 34",
                type = "지하철역"
            ),
            SearchAddressBuildingResult(
                result = "마곡 GUSEO 오피스텔",
                address = "서울시 마포구 흥정로 32, 34",
                type =  "건물"
            ),
            SearchAddressBuildingResult(
                result = "마곡 오피스텔",
                address = "서울시 마포구 흥정로 32, 36",
                type = "건물"
            )
        )
    }
    
}
