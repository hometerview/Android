package com.ftw.domain.usecase.login

import kotlinx.coroutines.flow.Flow

interface LoginSignInUseCase {
    suspend fun signIn() : Flow<Result<Boolean>>
}
