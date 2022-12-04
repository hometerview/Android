package com.ftw.domain.usecase.login

import com.ftw.domain.entity.KakaoToken
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    suspend fun signUp(kakaoToken: KakaoToken) : Result<Boolean>
    suspend fun signIn() : Flow<Result<Boolean>>
}
