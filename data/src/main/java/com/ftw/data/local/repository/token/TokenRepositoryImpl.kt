package com.ftw.data.local.repository.token

import com.ftw.domain.repository.token.TokenRepository

class TokenRepositoryImpl(
    private val tokenDataSource: TokenDataSource
) : TokenRepository {
    override fun get(): String {
        return ""
    }
}