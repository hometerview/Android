package com.ftw.domain.usecase

import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    operator fun invoke(): String

    suspend fun setUserToken(name: String)

    suspend fun getUserToken(): Flow<String?>
}