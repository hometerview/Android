package com.ftw.hometerview.di.usecase

import com.ftw.domain.usecase.buildingreview.GetBuildingReviewsUseCase
import com.ftw.domain.usecase.buildingreview.GetBuildingReviewsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BuildingReviewUseCaseModule {

    @Provides
    @Singleton
    fun provideGetBuildingReviewsUseCase(): GetBuildingReviewsUseCase {
        return GetBuildingReviewsUseCaseImpl()
    }
}
