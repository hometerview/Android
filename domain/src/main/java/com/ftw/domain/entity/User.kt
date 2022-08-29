package com.ftw.domain.entity

data class User(
    val nickName: String,
    val company: Company?
) {
    companion object {
        val NONE = User("", Company.NONE)
    }
}
