package com.ftw.data.network.datasource

import com.ftw.data.datasource.LoginDataSource
import com.ftw.data.remote.api.LoginAPI

<<<<<<< HEAD
class LoginRemoteDataSource(private val api: LoginAPI): LoginDataSource {
    override fun login(token: String): Result<String> {
        TODO("Not yet implemented")
    }
}
=======
class LoginRemoteDataSource(api: LoginAPI) : LoginDataSource {
    override fun login(token: String): Result<String> {
        return Result.success("")
    }
}
>>>>>>> develop
