package com.ftw.data.local.datasource

import androidx.datastore.preferences.core.stringPreferencesKey
import com.ftw.data.datasource.TokenDataSource
import com.ftw.data.local.datastore.DataStoreKeys
import com.ftw.data.local.datastore.DataStoreProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenLocalDataSource(private val provider: DataStoreProvider) : TokenDataSource {

    private val key = stringPreferencesKey(DataStoreKeys.USER_TOKEN)

    override suspend fun set(name: String) {
        provider.setValue(key, name)
    }

    override suspend fun get(): Flow<Result<String>> {
        return provider.getValue(key).map { token ->
            token?.let { Result.success(it) }
                ?: Result.failure(NoSuchFieldError("Getting token is failed"))
        }
    }
}
