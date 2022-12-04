package com.ftw.hometerview.di.usecase

import com.ftw.domain.repository.login.LoginRepository
import com.ftw.domain.repository.token.TokenRepository
import com.ftw.domain.usecase.login.LoginSignInUseCase
import com.ftw.domain.usecase.login.LoginSignInUseCaseImpl
import com.ftw.domain.usecase.login.LoginSignUpUseCase
import com.ftw.domain.usecase.login.LoginSignUpUseCaseImpl
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
    fun provideLoginSignUpUseCase(
        tokenRepository: TokenRepository,
        loginRepository: LoginRepository
    ): LoginSignUpUseCase {
        return LoginSignUpUseCaseImpl(tokenRepository, loginRepository)
    }

    @Provides
    @Singleton
    fun provideLoginSignInUseCase(
        tokenRepository: TokenRepository
    ): LoginSignInUseCase {
        return LoginSignInUseCaseImpl(tokenRepository)
    }
}
