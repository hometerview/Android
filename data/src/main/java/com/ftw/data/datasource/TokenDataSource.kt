package com.ftw.data.datasource

import kotlinx.coroutines.flow.Flow

interface TokenDataSource {
    suspend fun set(name: String)
    suspend fun get(): Flow<Result<String>>
}
