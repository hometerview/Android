package com.ftw.hometerview.di.repository

import com.ftw.data.repository.login.LoginRepositoryImpl
import com.ftw.domain.repository.login.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoginRepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(): LoginRepository {
        return LoginRepositoryImpl()
    }
}
