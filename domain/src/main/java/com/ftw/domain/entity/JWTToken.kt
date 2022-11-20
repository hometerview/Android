package com.ftw.domain.entity

data class JWTToken (
    val accessToken : String,
    val refreshToken : String
    )