package com.ftw.hometerview.ui.model

import android.os.Parcelable
import com.ftw.domain.entitiy.SearchResult
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelableSearchResult(
    val company: String,
    val address: String,
    val destination: String
) : Parcelable

fun SearchResult.toParcelable(): ParcelableSearchResult {
    return ParcelableSearchResult(
        company,
        address,
        destination
    )
}
