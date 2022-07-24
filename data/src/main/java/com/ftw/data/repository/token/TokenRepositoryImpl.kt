package com.ftw.data.repository.token

import com.ftw.data.datasource.TokenDataSource
import com.ftw.domain.repository.token.TokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenRepositoryImpl(
    private val dataSource: TokenDataSource
) : TokenRepository {

    override suspend fun setUserToken(name: String) {
        dataSource.set(name)
    }

    override suspend fun getUserToken(): Flow<String?> {
        return dataSource.get().map { it }
    }


}