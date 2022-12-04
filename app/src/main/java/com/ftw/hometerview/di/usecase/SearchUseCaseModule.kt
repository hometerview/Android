package com.ftw.hometerview.di.usecase

import com.ftw.domain.repository.search.SearchRepository
import com.ftw.domain.usecase.search.GetSearchedBuildingAddressesUseCase
import com.ftw.domain.usecase.search.GetSearchedBuildingAddressesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SearchUseCaseModule {
    @Provides
    fun provideSearchBuildingAddressesUseCaseImpl(
        repository: SearchRepository
    ): GetSearchedBuildingAddressesUseCase {
        return GetSearchedBuildingAddressesUseCaseImpl(repository)
    }
}
