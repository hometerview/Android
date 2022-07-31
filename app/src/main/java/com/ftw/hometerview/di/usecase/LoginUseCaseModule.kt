package com.ftw.hometerview.di.usecase

import com.ftw.domain.repository.login.LoginRepository
import com.ftw.domain.repository.token.TokenRepository
import com.ftw.domain.usecase.login.LoginUseCase
import com.ftw.domain.usecase.login.LoginUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoginUseCaseModule {

    @Provides
    @Singleton
    fun provideLoginUseCase(
        tokenRepository: TokenRepository,
        loginRepository: LoginRepository
    ): LoginUseCase {
        return LoginUseCaseImpl(tokenRepository, loginRepository)
    }
}
