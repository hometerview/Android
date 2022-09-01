package com.ftw.data.remote.response.reviews

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Price(
    @SerializedName("deposit")
    val deposit: String,
    @SerializedName("maintainFee")
    val maintainFee: String,
    @SerializedName("monthly")
    val monthly: String
) : Parcelable
