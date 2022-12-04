package com.ftw.hometerview.di.datasource

import com.ftw.data.datasource.LoginDataSource
import com.ftw.data.remote.datasource.LoginRemoteDataSource
import com.ftw.data.remote.api.SignUpAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoginDataSourceModule {

    @Provides
    @Singleton
    fun provideLoginDataSource(api: SignUpAPI): LoginDataSource {
        return LoginRemoteDataSource(api)
    }
}
