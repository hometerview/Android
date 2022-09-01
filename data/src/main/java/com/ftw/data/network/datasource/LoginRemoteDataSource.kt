package com.ftw.data.network.datasource

import com.ftw.data.datasource.LoginDataSource
import com.ftw.data.remote.api.LoginAPI

class LoginRemoteDataSource(private val api: LoginAPI) : LoginDataSource {
    override suspend fun login(token: String): Result<String> {
        return Result.success("")
    }
}
