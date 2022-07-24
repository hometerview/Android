package com.ftw.domain.repository.token

import kotlinx.coroutines.flow.Flow

interface TokenRepository {

    suspend fun setUserToken(name: String)

    suspend fun getUserToken(): Flow<String?>

}