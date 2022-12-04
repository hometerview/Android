package com.ftw.data.repository.token

import com.ftw.data.datasource.TokenDataSource
import com.ftw.domain.entity.JWTToken
import com.ftw.domain.repository.token.TokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenRepositoryImpl(
    private val dataSource: TokenDataSource
) : TokenRepository {

    override suspend fun setUserToken(jwtToken: JWTToken){
        dataSource.set(jwtToken)
    }

    override suspend fun getUserToken(): Flow<Result<Boolean>> {
        return dataSource.hasToken().map { hasToken ->
            if (hasToken) Result.success(true)
            else Result.failure(IllegalAccessException("로컬에 데이터가 없습니다"))
        }
    }
}
