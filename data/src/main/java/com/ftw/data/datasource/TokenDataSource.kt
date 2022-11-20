package com.ftw.data.datasource

import com.ftw.domain.entity.JWTToken
import kotlinx.coroutines.flow.Flow

interface TokenDataSource {
    suspend fun set(jwtToken: JWTToken)
    suspend fun get(): Flow<Result<String>>
}
