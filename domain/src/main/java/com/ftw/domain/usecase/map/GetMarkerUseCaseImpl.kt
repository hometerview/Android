package com.ftw.domain.usecase.map

import com.ftw.domain.entity.StationMarker

class GetMarkerUseCaseImpl : GetMarkerUseCase {
    override suspend fun invoke(location: String): List<StationMarker> {
        return listOf(
            StationMarker(
                station = "강남구청역",
                latitude = 37.51732701554125,
                longitude = 127.04125331828345,
                buildingCnt = 38)
            ,
            StationMarker(
                station = "학동역",
                latitude = 37.51450076427542,
                longitude = 127.03188613098999,
                buildingCnt = 24
            ),
            StationMarker(
                station = "선정릉역",
                latitude = 37.51042438956853,
                longitude = 127.04385094188316,
                buildingCnt = 24
            ),
            StationMarker(
                station = "언주역",
                latitude = 37.50745434356066,
                longitude = 127.03391894910082,
                buildingCnt = 105
            ),
            StationMarker(
                station = "신논현역",
                latitude = 37.50468158441123,
                longitude = 127.02446281227596,
                buildingCnt = 1705
            ),
            StationMarker(
                station = "강남역",
                latitude = 37.498157604159395,
                longitude = 127.0276271154474,
                buildingCnt = 67
            ),
            StationMarker(
                station = "역삼역",
                latitude = 37.500750102391144,
                longitude = 127.0364717670947,
                buildingCnt = 13
            )
        )
    }

}
