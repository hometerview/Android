package com.ftw.data.repository.login

import com.ftw.domain.repository.login.LoginRepository

class LoginRepositoryImpl : LoginRepository {
    override fun login(token: String): Result<String> {
        return Result.success("temporary token")
    }
}
