package com.ftw.domain.repository.login

import com.ftw.domain.entity.JWTToken
import com.ftw.domain.entity.KakaoToken

interface LoginRepository {
    suspend fun setKaKaoToken(kakaoToken: KakaoToken) : Result<JWTToken>
}
