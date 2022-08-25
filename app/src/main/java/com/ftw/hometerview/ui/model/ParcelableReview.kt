package com.ftw.hometerview.ui.model

import android.os.Parcelable
import com.ftw.domain.entity.BuildingType
import com.ftw.domain.entity.Review
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class ParcelableReview(
    val buildingId: Long,
    val buildingName: String,
    val buildingType: BuildingType,
    val buildingAddress: String,
    val rating: Int,
    val summary: String,
    val officeLocation: String,
    val advantage: String,
    val disadvantage: String,
    val favorite: Boolean,
    val leftAt: Date
) : Parcelable

fun Review.toParcelable() = ParcelableReview(
    buildingId,
    buildingName,
    buildingType,
    buildingAddress,
    rating,
    summary,
    officeLocation,
    advantage,
    disadvantage,
    favorite,
    leftAt
)
