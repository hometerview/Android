package com.ftw.domain.entity

import java.util.Date

data class Review(
//    val advantage: String,
//    val bookmarkCount: Int,
//    val buildingId: String,
//    val certification: List<String>,
//    val companyId: String,
//    val disadvantage: String,
//    val leftAt: Date,
//    val likeCount: Int,
//    val memberId: String,
//    val residentialPeriod: String,
//    val price: Price,
//    val rating: Int,
//
//    val isBookmarked: Boolean,
//    val isLiked: Boolean

//    // 별점에 따른 요약멘트 : 이건 뭐 로컬에서 ㅇㅋ
//    val summary: String,
//
//    // 거주 층
//    val residentialFloor: String,
//    // 회사 지역
//    val officeLocation: String,
//
// //    val buildingName: String,
// //    val buildingType: BuildingType,
// //    val buildingAddress: String,

    val buildingId: Long,
    val buildingName: String,
    val buildingType: BuildingType,
    val buildingAddress: String,
    val rating: Int,
    val summary: String,
    val residentialPeriod: String,
    val residentialFloor: String,
    val officeLocation: String,
    val advantage: String,
    val disadvantage: String,
    val favorite: Boolean,
    val leftAt: Date

) {
    companion object {
        val NONE = Review(0, "", BuildingType.OFFICETEL, "", 0, "", "", "", "", "", "", false, Date())
//        val NONE = Review("", 0, "", listOf(), "", "", Date(), 0, "", "", Price("", "", ""), 0, false, false)
    }
}
