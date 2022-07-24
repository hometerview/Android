package com.ftw.data.local.repository.login

import androidx.datastore.preferences.preferencesKey
import com.ftw.data.local.datasource.local.DataStoreProvider.getValue
import com.ftw.data.local.datasource.local.DataStoreProvider.setValue
import com.ftw.data.local.repository.login.ILoginDataStoreRepository.Keys.USER_TOKEN
import com.ftw.domain.repository.login.LoginDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ILoginDataStoreRepository: LoginDataStoreRepository {

    override suspend fun setUserToken(name: String) {
        setValue(USER_TOKEN, name)
    }

    override suspend fun getUserToken(): Flow<String?> {
        return getValue(USER_TOKEN).map { it }
    }

    object Keys {
        val USER_TOKEN = preferencesKey<String>("user_token")
    }
}
