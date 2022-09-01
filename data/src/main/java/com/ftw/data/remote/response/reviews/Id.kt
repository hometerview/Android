package com.ftw.data.remote.response.reviews

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Id(
    @SerializedName("date")
    val date: String,
    @SerializedName("timestamp")
    val timestamp: Int
) : Parcelable
