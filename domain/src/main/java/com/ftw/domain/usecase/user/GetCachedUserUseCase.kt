package com.ftw.domain.usecase.user

import com.ftw.domain.entity.User

interface GetCachedUserUseCase {
    operator fun invoke(): User
}
