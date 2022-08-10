package com.ftw.domain.repository.login

interface LoginRepository {
    suspend fun login(token: String): Result<String>
}
