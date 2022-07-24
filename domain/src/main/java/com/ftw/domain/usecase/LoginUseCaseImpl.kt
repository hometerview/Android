package com.ftw.domain.usecase

import com.ftw.domain.repository.login.LoginDataStoreRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCaseImpl(private val loginDataStoreRepository: LoginDataStoreRepository) : LoginUseCase {
    override fun invoke(): String {


        // 로그인 API 호출
        // 얻어온 token 저장
        return "hi"
    }

    override suspend fun setUserToken(name: String) {
        loginDataStoreRepository.setUserToken(name)
    }

    override suspend fun getUserToken(): Flow<String?> {
        return loginDataStoreRepository.getUserToken()
    }
}