package com.ftw.hometerview.di.usecase

import com.ftw.domain.usecase.review.GetLocaionReviewsUseCase
import com.ftw.domain.usecase.review.GetLocationReviewsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ReviewUseCaseModule {
    @Provides
    @Singleton
    fun provideGetLocationReviewsUseCase(): GetLocaionReviewsUseCase{
        return GetLocationReviewsUseCaseImpl()
    }
}
