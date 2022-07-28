package com.ftw.domain.usecase.token

import kotlinx.coroutines.flow.Flow

interface GetStoredTokenUseCase {
    suspend operator fun invoke(): Flow<Result<String>>
}
