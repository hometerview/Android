package com.ftw.data.datasource

import com.ftw.domain.entity.JWTToken
import com.ftw.domain.entity.KakaoToken

interface LoginDataSource {
    suspend fun setKakaoToken(kakaoToken: KakaoToken): Result<JWTToken>
}
