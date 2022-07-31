package com.ftw.hometerview.di.usecase

import com.ftw.domain.repository.token.TokenRepository
import com.ftw.domain.usecase.token.GetStoredTokenUseCase
import com.ftw.domain.usecase.token.GetStoredTokenUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TokenUseCaseModule {

    @Provides
    @Singleton
    fun provideGetStoredTokenUseCase(tokenRepository: TokenRepository): GetStoredTokenUseCase {
        return GetStoredTokenUseCaseImpl(tokenRepository)
    }
}
