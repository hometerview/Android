package com.ftw.hometerview.di.repository

import com.ftw.data.datasource.search.SearchDataSource
import com.ftw.data.repository.search.SearchRepositoryImpl
import com.ftw.domain.repository.search.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchRepositoryModule {

    @Provides
    @Singleton
    fun provideSearchRepository(dataSource: SearchDataSource): SearchRepository {
        return SearchRepositoryImpl(dataSource)
    }
}
