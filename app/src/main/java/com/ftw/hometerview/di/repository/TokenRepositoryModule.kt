package com.ftw.hometerview.di.repository

import com.ftw.data.datasource.TokenDataSource
import com.ftw.data.repository.token.TokenRepositoryImpl
import com.ftw.domain.repository.token.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TokenRepositoryModule {

    @Provides
    @Singleton
    fun provideTokenRepository(dataSource: TokenDataSource): TokenRepository {
        return TokenRepositoryImpl(dataSource)
    }
}
