package com.ftw.data.repository.login

import com.ftw.data.datasource.LoginDataSource
import com.ftw.domain.repository.login.LoginRepository

class LoginRepositoryImpl(private val loginDataSource: LoginDataSource) : LoginRepository {
    override suspend fun login(token: String): Result<String> {
        // TODO: token 으로 login 하는 로직 추가
        return loginDataSource.login("temporary token")
    }
}
