package com.ftw.data.local.datasource

import androidx.datastore.preferences.core.stringPreferencesKey
import com.ftw.data.datasource.TokenDataSource
import com.ftw.data.local.datastore.DataStoreKeys
import com.ftw.data.local.datastore.DataStoreProvider
import com.ftw.domain.entity.JWTToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenLocalDataSource(private val provider: DataStoreProvider) : TokenDataSource {

    private val accessKey = stringPreferencesKey(DataStoreKeys.USER_ACCESS_TOKEN)
    private val refreshKey = stringPreferencesKey(DataStoreKeys.USER_REFRESH_TOKEN)

    override suspend fun set(jwtToken: JWTToken) {
        provider.setValue(accessKey, jwtToken.accessToken)
        provider.setValue(refreshKey,jwtToken.refreshToken)
    }

    override suspend fun get(): Flow<Result<String>> {
        return provider.getValue(accessKey).map { token ->
            token?.let { Result.success(it) }
                ?: Result.failure(NoSuchFieldError("Getting token is failed"))
        }
    }
}
