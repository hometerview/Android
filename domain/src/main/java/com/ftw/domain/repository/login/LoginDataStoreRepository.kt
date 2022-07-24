package com.ftw.domain.repository.login

import kotlinx.coroutines.flow.Flow

interface LoginDataStoreRepository {

    suspend fun setUserToken(name: String)

    suspend fun getUserToken(): Flow<String?>

}