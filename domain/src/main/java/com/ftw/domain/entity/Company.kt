package com.ftw.domain.entity

data class Company(
    val name: String,
    val location: String
) {
    companion object {
        val NONE = Company("", "")
    }
}
