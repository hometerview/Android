package com.ftw.domain.usecase.login

import com.ftw.domain.entity.KakaoToken

interface LoginUseCase {
    suspend fun signUp(kakaoToken: KakaoToken) : Result<Boolean>
}
