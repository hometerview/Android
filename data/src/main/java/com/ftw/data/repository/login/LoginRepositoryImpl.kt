package com.ftw.data.repository.login

import com.ftw.data.datasource.LoginDataSource
import com.ftw.domain.entity.JWTToken
import com.ftw.domain.entity.KakaoToken
import com.ftw.domain.repository.login.LoginRepository

class LoginRepositoryImpl(private val loginDataSource: LoginDataSource) : LoginRepository {

    override suspend fun setKaKaoToken(kakaoToken: KakaoToken): Result<JWTToken> { //근데 실패하면 뭐가 나오는지 궁금함
        return loginDataSource.setKakaoToken(kakaoToken).let { it }
    }
}
