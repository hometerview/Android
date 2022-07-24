package com.ftw.domain.usecase.login

import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    operator fun invoke(): String

}