package com.ftw.domain.usecase.login

import com.ftw.domain.repository.token.TokenRepository
import kotlinx.coroutines.flow.Flow

class LoginSignInUseCaseImpl(private val tokenRepository: TokenRepository) : LoginSignInUseCase {
    override suspend fun signIn(): Flow<Result<Boolean>> = tokenRepository.getUserToken()
}
