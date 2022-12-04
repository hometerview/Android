package com.ftw.data.remote.datasource

import com.ftw.data.datasource.LoginDataSource
import com.ftw.data.remote.api.SignUpAPI
import com.ftw.data.remote.exception.ResponseException
import com.ftw.data.remote.request.KakaoTokenReq
import com.ftw.domain.entity.JWTToken
import com.ftw.domain.entity.KakaoToken

class LoginRemoteDataSource(private val api: SignUpAPI) : LoginDataSource {
    override suspend fun setKakaoToken(kakaoToken: KakaoToken): Result<JWTToken> {
        return try {
            val response = api.create(KakaoTokenReq(kakaoToken.accessToken,kakaoToken.refreshToken))
            if (response.isSuccessful) {
                val accessToken = response.headers().get("Authorization-Access-Token")
                val refreshToken = response.headers().get("Authorization-Refresh-Token")
                if (accessToken != null && refreshToken != null) {
                    return Result.success(JWTToken(accessToken,refreshToken))
                }else {
                    throw ResponseException("Token is Null Exception")
                }
            } else {
                throw ResponseException("Network Exception")
            }
        } catch (e : Exception) {
            return Result.failure(e)
        }
    }
}
