package com.ftw.domain.usecase.login

import com.ftw.domain.entity.KakaoToken
import com.ftw.domain.repository.login.LoginRepository
import com.ftw.domain.repository.token.TokenRepository
import kotlinx.coroutines.flow.Flow

class LoginSignUpUseCaseImpl(
    private val tokenRepository: TokenRepository,
    private val loginRepository: LoginRepository
) : LoginSignUpUseCase {

    //1. 서버에 카카오 토큰을 넘겨 서버에 저장
    //2. 받아올 jwt토큰을 로컬에 저장
    override suspend fun signUp(kakaoToken: KakaoToken): Result<Boolean> {
        val token = loginRepository.setKaKaoToken(kakaoToken).getOrNull() ?: return Result.failure(
            IllegalAccessException("signUp 실패")
        )
        tokenRepository.setUserToken(token)
        return Result.success(true)
    }

}
