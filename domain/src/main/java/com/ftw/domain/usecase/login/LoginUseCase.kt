package com.ftw.domain.usecase.login

import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    suspend operator fun invoke(): Flow<Result<String>>
}
