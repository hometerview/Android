package com.ftw.hometerview.di.usecase

import com.ftw.domain.repository.review.ReviewRepository
import com.ftw.domain.usecase.review.CreateReviewUseCase
import com.ftw.domain.usecase.review.CreateReviewUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ReviewUseCaseModule {
    @Provides
    fun provideCreateReviewUseCase(repository: ReviewRepository): CreateReviewUseCase {
        return CreateReviewUseCaseImpl(repository)
    }
}
