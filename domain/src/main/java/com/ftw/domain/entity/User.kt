package com.ftw.domain.entity

data class User(
    val company: Company?
) {
    companion object {
        val NONE = User(Company.NONE)
    }
}
