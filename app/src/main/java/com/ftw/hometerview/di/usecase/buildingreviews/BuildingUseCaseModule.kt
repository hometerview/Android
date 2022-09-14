package com.ftw.hometerview.di.usecase.buildingreviews

import com.ftw.domain.usecase.buildingreviews.GetBuildingUseCase
import com.ftw.domain.usecase.buildingreviews.GetBuildingUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BuildingUseCaseModule {

    @Provides
    @Singleton
    fun provideGetBuildingUseCase(): GetBuildingUseCase {
        return GetBuildingUseCaseImpl()
    }
}
