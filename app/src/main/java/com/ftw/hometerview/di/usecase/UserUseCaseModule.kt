package com.ftw.hometerview.di.usecase

import com.ftw.domain.usecase.user.GetCachedUserUseCase
import com.ftw.domain.usecase.user.GetCachedUserUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserUseCaseModule {

    @Provides
    @Singleton
    fun provideGetCachedUseCase(): GetCachedUserUseCase {
        return GetCachedUserUseCaseImpl()
    }
}
