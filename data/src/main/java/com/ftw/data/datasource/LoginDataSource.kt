package com.ftw.data.datasource

interface LoginDataSource {
    fun login(token: String): Result<String>
}
