package com.ftw.data.datasource

interface LoginDataSource {
    suspend fun login(token: String): Result<String>
}
