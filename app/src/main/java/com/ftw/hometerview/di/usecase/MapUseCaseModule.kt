package com.ftw.hometerview.di.usecase

import com.ftw.domain.usecase.map.GetBuildingMarkerUseCase
import com.ftw.domain.usecase.map.GetBuildingMarkerUseCaseImpl
import com.ftw.domain.usecase.map.GetMarkerUseCase
import com.ftw.domain.usecase.map.GetMarkerUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapUseCaseModule {
    @Provides
    @Singleton
    fun provideGetMarkerUseCase(): GetMarkerUseCase {
        return GetMarkerUseCaseImpl()
    }
    @Provides
    @Singleton
    fun provideGetBuildingMarkerUseCase(): GetBuildingMarkerUseCase {
        return GetBuildingMarkerUseCaseImpl()
    }
}
