package com.ftw.data.remote.response.review

import com.ftw.domain.entity.Price
import com.ftw.domain.entity.TestReview
import com.google.gson.annotations.SerializedName
import java.util.Date

data class BuildingReviewResponse(
    @SerializedName("isBookmarked")
    val isBookmarked: Boolean?,
    @SerializedName("isLiked")
    val isLiked: Boolean?,
    @SerializedName("review")
    val review: ReviewResponse
)

fun BuildingReviewResponse.toTestReview(): TestReview {
    return TestReview(
        advantage = review.advantage,
        bookmarkCount = review.bookmarkCount,
        buildingId = review.buildingId,
        certification = review.certification,
        companyId = review.companyId,
        disadvantage = review.disadvantage,
        leftAt = Date(),
        likeCount = review.likeCount,
        memberId = review.memberId,
        residentialPeriod = review.period,
        price = Price(
            review.priceResponse.deposit,
            review.priceResponse.maintainFee,
            review.priceResponse.monthly
        ),
        rating = review.rating,
        isBookmarked = isBookmarked == true,
        isLiked = isLiked == true
    )
}
