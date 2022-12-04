package com.ftw.data.remote.response.address

import com.ftw.domain.entity.Address
import com.google.gson.annotations.SerializedName

data class AddressResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)

fun AddressResponse.toEntity(): Address {
    return Address(id, name)
}
