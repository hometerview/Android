package com.ftw.hometerview.di.datasource

import com.ftw.data.datasource.search.SearchDataSource
import com.ftw.data.remote.api.SearchAPI
import com.ftw.data.remote.datasource.search.SearchRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchDataSourceModule {

    @Provides
    @Singleton
    fun provideSearchDataSource(searchAPI: SearchAPI): SearchDataSource {
        return SearchRemoteDataSource(searchAPI)
    }
}
