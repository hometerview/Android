package com.ftw.data.repository.token

import com.ftw.data.datasource.TokenDataSource
import com.ftw.domain.repository.token.TokenRepository
import kotlinx.coroutines.flow.Flow

class TokenRepositoryImpl(
    private val dataSource: TokenDataSource
) : TokenRepository {

    override suspend fun setUserToken(name: String) {
        dataSource.set(name)
    }

    override suspend fun getUserToken(): Flow<Result<String>> {
        return dataSource.get()
    }
}
