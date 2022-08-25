package com.ftw.hometerview.ui.model

import android.os.Parcelable
import com.ftw.domain.entity.SearchCompanyResult
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelableSearchCompanyResult(
    val company: String,
    val address: String,
    val destination: String
) : Parcelable

fun SearchCompanyResult.toParcelable(): ParcelableSearchCompanyResult {
    return ParcelableSearchCompanyResult(
        company,
        address,
        destination
    )
}
