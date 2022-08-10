package com.ftw.domain.entity

data class Address(
    val longitude: Long,
    val latitude: Long,
    val addressWithLoadName: String,
    val addressWithHouseNumber: String
)
