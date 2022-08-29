package com.ftw.hometerview.di.usecase

import com.ftw.domain.usecase.searchaddressbuilding.GetSearchAddressBuildingUseCase
import com.ftw.domain.usecase.searchaddressbuilding.GetSearchAddressBuildingUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchAddressBuildingUseCaseModule {
    @Provides
    @Singleton
    fun provideGetSearchAddressBuildingUseCase(): GetSearchAddressBuildingUseCase {
        return GetSearchAddressBuildingUseCaseImpl()
    }
}
