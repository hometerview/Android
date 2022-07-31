package com.ftw.domain.usecase.login

import com.ftw.domain.repository.login.LoginRepository
import com.ftw.domain.repository.token.TokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginUseCaseImpl(
    private val tokenRepository: TokenRepository,
    private val loginRepository: LoginRepository
) : LoginUseCase {
    override suspend fun invoke(): Flow<Result<String>> {
        val tokenResult = tokenRepository.getUserToken()
        return tokenResult.map { result ->
            result.getOrNull()?.let { token -> loginRepository.login(token) }
                ?: Result.failure(IllegalAccessException("Login is failed"))
        }
    }
}
