package com.ftw.data.mapper

import android.annotation.SuppressLint
import com.ftw.domain.entity.Price
import com.ftw.domain.entity.TestReview
import java.text.SimpleDateFormat
import java.util.*

object ReviewsMapper {

    fun mapperToReview(review: com.ftw.data.remote.response.reviews.Review): TestReview {
        return TestReview(
            advantage = review.review.advantage,
            bookmarkCount = review.review.bookmarkCount,
            buildingId = review.review.buildingId,
            certification = review.review.certification,
            companyId = review.review.companyId,
            disadvantage = review.review.disadvantage,
            leftAt = Date(),
            likeCount = review.review.likeCount,
            memberId = review.review.memberId,
            residentialPeriod = review.review.period,
            price = Price(
                review.review.price.deposit,
                review.review.price.maintainFee,
                review.review.price.monthly
            ),
            rating = review.review.rating,
            isBookmarked = review.isBookmarked == true,
            isLiked = review.isLiked == true
        )
    }
}

@SuppressLint("SimpleDateFormat")
fun String.makeDate(): Date {
    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.parse(this.substring(0, 10)) as Date
}
