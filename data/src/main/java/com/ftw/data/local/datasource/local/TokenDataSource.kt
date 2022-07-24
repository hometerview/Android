package com.ftw.data.local.datasource.local

import kotlinx.coroutines.flow.Flow

class TokenDataSource(private val provider: DataStoreProvider) {
    fun get(): Flow<String> {
        return provider.getValue("token")
    }
}