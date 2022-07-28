package com.ftw.hometerview.di.datasource

import com.ftw.data.datasource.TokenDataSource
import com.ftw.data.local.datasource.TokenLocalDataSource
import com.ftw.data.local.datastore.DataStoreProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TokenDataSourceModule {

    @Provides
    @Singleton
    fun provideTokenDataSource(dataStoreProvider: DataStoreProvider): TokenDataSource {
        return TokenLocalDataSource(dataStoreProvider)
    }
}
