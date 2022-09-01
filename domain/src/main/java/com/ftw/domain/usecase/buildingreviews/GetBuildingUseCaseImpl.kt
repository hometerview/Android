package com.ftw.domain.usecase.buildingreviews

import com.ftw.domain.entity.Address
import com.ftw.domain.entity.Building
import com.ftw.domain.entity.BuildingType
import com.ftw.domain.entity.Price

class GetBuildingUseCaseImpl : GetBuildingUseCase {
//    override suspend fun invoke(buildingId: Long): Building {
//        return Building(
//            id = 1,
//            name = "아크로텔 오피스텔",
//            type = BuildingType.OFFICETEL,
//            address = Address(
//                longitude = 123,
//                latitude = 123,
//                addressWithLoadName = "인천 남동구 구월남로 125",
//                addressWithHouseNumber = "구월동 1133-4"
//            ),
//            salePrice = Price(
//                min = "4억 5,500",
//                max = "5억 8,000"
//            ),
//            charterPrice = Price(
//                min = "8억 1,500",
//                max = "10억 2,000"
//            ),
//            rating = 4,
//            favorite = false
//        )
//    }

    override suspend fun invoke(buildingId: String): Building {
        return Building(
            id = 1,
            name = "아크로텔 오피스텔",
            type = BuildingType.OFFICETEL,
            address = Address(
                longitude = 123,
                latitude = 123,
                addressWithLoadName = "인천 남동구 구월남로 125",
                addressWithHouseNumber = "구월동 1133-4"
            ),
            salePrice = Price(
                deposit = "4억 5,500",
                maintainFee = "5억 8,000",
                monthly = "5억 8,000"
            ),
            charterPrice = Price(
                deposit = "8억 1,500",
                maintainFee = "10억 2,000",
                monthly = "5억 8,000"
            ),
            rating = 4,
            favorite = false
        )
    }

    /*

    private fun getTempReview1(): Review {
        return Review(
            buildingId = 0,
            buildingName = "아크로텔 오피스텔",
            buildingType = BuildingType.OFFICETEL,
            buildingAddress = "인천 남동구 구월남로 125",
            rating = 4,
            summary = "내 집이 최고",
            residentialPeriod = "3년",
            residentialFloor = "고층",
            officeLocation = "역삼역",
            advantage = "역근처라 그런지 일단 인프라가 너무 좋아요!",
            disadvantage = "창문이 많아서 냉난방비 많이나오고 커튼달기도 쉽지 않음... 그리고 새거라 석면가루같은게 막 나오고 그럼.\n그래도 살만합니다.",
            favorite = false,
            leftAt = Date().apply {
                Calendar.getInstance().apply {
                    set(2020, 12, 30)
                }
            }
        )
    }

    private fun getTempReview2(): Review {
        return Review(
            buildingId = 0,
            buildingName = "아크로텔 오피스텔",
            buildingType = BuildingType.OFFICETEL,
            buildingAddress = "인천 남동구 구월남로 125",
            rating = 3,
            summary = "내 집이 최고",
            residentialPeriod = "1년",
            residentialFloor = "중층",
            officeLocation = "삼전역",
            advantage = "지하철 가깝고, 내부 도배, 가구들 깔끔하고, 구조 괜팒습니다. 화장실도 괜찮은 편이고 이중창이라 단열 잘되구요.",
            disadvantage = "정말 작습니다. 1층 문앞에 외부인들 매일 담배피고 침 뱉습니다ㅣ. 비번입력 잘 안돼서 배달 시키면 1층 내려가서 받아야 함. 남향이라고 했는데 앞에 건물 때문에 직광 전혀 없습니다. 그냥 북향이라고 보면 됨",
            favorite = false,
            leftAt = Date().apply {
                Calendar.getInstance().apply {
                    set(2021, 11, 3)
                }
            }
        )
    }

    private fun getTempReview3(): Review {
        return Review(
            buildingId = 0,
            buildingName = "아크로텔 오피스텔",
            buildingType = BuildingType.OFFICETEL,
            buildingAddress = "인천 남동구 구월남로 125",
            rating = 1,
            summary = "내 집이 최고",
            residentialPeriod = "2년",
            residentialFloor = "중층",
            officeLocation = "몽촌토성역",
            advantage = "관리비 8만원 월세에 포함되어있음\n- 주변에 버스 정류장이 많아 평택역이나 시내에 나가기 편함\n- 건물 내부 엘리베이터 있음\n- 주차공간14개\n- 주변에 신축공원 공사 중",
            disadvantage = "건물 관리인이 노답임. 통화도 어렵고 어렵게 통화가 되더라도 말이 안 통함. 집주인과 직통으로 연락하는 것이 아닌 건물 관리인이 중간에 있기 때문에 건물 관리인의 역할이 중요하다 진짜 욕나오게 하는 관리인임. 아래는 건무루 관리인과 있었던 트러블(입주 두 달 동안) \n1. 입주한지 두 달 밖에 안 된 월세 세입자를 입주 2년 넘은 전세 세입자로 착각. \n2. 전화통화 어려움. 문자 답장 또한 없음\n3. 월세 확인도 제대로 안 됨. \n 4. 방 내부 관리가 전혀 안 되어있어(화장실 문 안 닫힘, 화장실 내부 창 흔들림 등) 요청하였으나 알겠다고 하고는 그 이후 연락이없음. 화를 내야 그제서야 고쳐줌 \n5. 집이 낡았다는 이유로 방 내부에 있는 옵션 수리 안 해줌",
            favorite = false,
            leftAt = Date().apply {
                Calendar.getInstance().apply {
                    set(2022, 3, 2)
                }
            }
        )
    }
     */
}
