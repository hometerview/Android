package com.ftw.data.repository.login

import com.ftw.data.datasource.LoginDataSource
import com.ftw.domain.entity.JWTToken
import com.ftw.domain.entity.KakaoToken
import com.ftw.domain.repository.login.LoginRepository

class LoginRepositoryImpl(private val loginDataSource: LoginDataSource) : LoginRepository {

    override suspend fun setKaKaoToken(kakaoToken: KakaoToken): Result<JWTToken> {
        val result = loginDataSource.setKakaoToken(kakaoToken)
        result.getOrNull()?.let {
            return Result.success(it)
        }
        return Result.failure(IllegalAccessException("LoginRepositoryImpl에러"))
    }
}
