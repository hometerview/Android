package com.ftw.domain.usecase.login

import com.ftw.domain.repository.login.LoginRepository

class LoginUseCaseImpl(private val loginDataStoreRepository: LoginRepository) : LoginUseCase {
    override fun invoke(): String {


        // 로그인 API 호출
        // 얻어온 token 저장
        return "hi"
    }

}