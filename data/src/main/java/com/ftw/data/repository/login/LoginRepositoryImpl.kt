package com.ftw.data.repository.login

import com.ftw.domain.repository.login.LoginRepository

class LoginRepositoryImpl : LoginRepository {
    override fun login(token: String): Result<String> {
        // TODO: token 으로 login 하는 로직 추가
        return Result.success("temporary token")
    }
}
