package com.ftw.domain.usecase.user

import com.ftw.domain.entity.Company
import com.ftw.domain.entity.User

class GetCachedUserUseCaseImpl : GetCachedUserUseCase {
    override fun invoke(): User {
        // TODO: UserRepository 추가 및 return value 수정
        return User(
            nickName = "길동씨",
            company = Company("삼성전자", "동탄역")
        )
    }
}
