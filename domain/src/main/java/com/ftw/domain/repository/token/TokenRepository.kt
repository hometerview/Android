package com.ftw.domain.repository.token

import com.ftw.domain.entity.JWTToken
import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun setUserToken(jwtToken: JWTToken)
    suspend fun getUserToken(): Flow<Result<Boolean>>
}
