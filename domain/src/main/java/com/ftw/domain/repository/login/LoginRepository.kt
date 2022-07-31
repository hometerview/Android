package com.ftw.domain.repository.login

interface LoginRepository {
    fun login(token: String): Result<String>
}
