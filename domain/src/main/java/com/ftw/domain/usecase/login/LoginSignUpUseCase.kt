package com.ftw.domain.usecase.login

import com.ftw.domain.entity.KakaoToken

interface LoginSignUpUseCase {
    suspend fun signUp(kakaoToken: KakaoToken) : Result<Boolean>
}
