package com.ftw.hometerview.di.usecase.buildingreviews

import com.ftw.domain.usecase.buildingreviews.GetReviewsUseCase
import com.ftw.domain.usecase.buildingreviews.GetReviewsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ReviewsUseCaseModule {

    @Provides
    @Singleton
    fun provideGetReviewsUseCase(): GetReviewsUseCase {
        return GetReviewsUseCaseImpl()
    }
}
