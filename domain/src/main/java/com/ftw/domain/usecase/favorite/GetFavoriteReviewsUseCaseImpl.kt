package com.ftw.domain.usecase.favorite

import com.ftw.domain.entity.FavoriteReview
import java.util.Calendar
import java.util.Date

class GetFavoriteReviewsUseCaseImpl : GetFavoriteReviewsUseCase {
    override suspend fun invoke(): List<FavoriteReview> {
        return listOf(
            FavoriteReview(
                buildingId = 0,
                buildingName = "아크로텔 오피스텔",
                rating = 4,
                summary = "내 집이 최고",
                officeLocation = "역삼역",
                savedAdvantage = "역근처라 그런지 일단 인프라가 !관심 리뷰! 너무 좋아요!",
                savedDisadvantage = "창문이 많아서 냉난방비 !관심 리뷰! 많이나오고 커튼달기도 쉽지 않음... 그리고 새거라 석면가루같은게 막 나오고 그럼.\n그래도 살만합니다.",
                favorite = false,
                leftAt = Date().apply {
                    Calendar.getInstance().apply {
                        set(2020, 12, 30)
                    }
                }
            ),
            FavoriteReview(
                buildingId = 0,
                buildingName = "한국 아파트",
                rating = 3,
                summary = "내 집이 쵝오",
                officeLocation = "삼전역",
                savedAdvantage =  "지하철 가깝고, 내부 도배, 가구들 깔끔하고, 구조 괜팒습니다. 화장실도 괜찮은 편이고 이중창이라 단열 잘되구요.",
                savedDisadvantage = "정말 작습니다. 1층 문앞에 외부인들 매일 담배피고 침 뱉습니다ㅣ. 비번입력 잘 안돼서 배달 시키면 1층 내려가서 받아야 함. 남향이라고 했는데 앞에 건물 때문에 직광 전혀 없습니다. 그냥 북향이라고 보면 됨",
                favorite = false,
                leftAt = Date().apply {
                    Calendar.getInstance().apply {
                        set(2021, 11, 3)
                    }
                }
            ),
            FavoriteReview(
                buildingId = 0,
                buildingName = "한빛 삼성 아파트",
                rating = 5,
                summary = "내 집이 최고얌",
                officeLocation = "몽촌토성역",
                savedAdvantage = "관리비 8만원 월세에 포함되어있음\n- 주변에 버스 정류장이 많아 평택역이나 시내에 나가기 편함\n- 건물 내부 엘리베이터 있음\n- 주차공간14개\n- 주변에 신축공원 공사 중",
                savedDisadvantage = "건물 관리인이 노답임. 통화도 어렵고 어렵게 통화가 되더라도 말이 안 통함. 집주인과 직통으로 연락하는 것이 아닌 건물 관리인이 중간에 있기 때문에 건물 관리인의 역할이 중요하다 진짜 욕나오게 하는 관리인임. 아래는 건무루 관리인과 있었던 트러블(입주 두 달 동안) \n1. 입주한지 두 달 밖에 안 된 월세 세입자를 입주 2년 넘은 전세 세입자로 착각. \n2. 전화통화 어려움. 문자 답장 또한 없음\n3. 월세 확인도 제대로 안 됨. \n 4. 방 내부 관리가 전혀 안 되어있어(화장실 문 안 닫힘, 화장실 내부 창 흔들림 등) 요청하였으나 알겠다고 하고는 그 이후 연락이없음. 화를 내야 그제서야 고쳐줌 \n5. 집이 낡았다는 이유로 방 내부에 있는 옵션 수리 안 해줌",
                favorite = false,
                leftAt = Date().apply {
                    Calendar.getInstance().apply {
                        set(2022, 3, 2)
                    }
                }
            )
        )
    }

}
