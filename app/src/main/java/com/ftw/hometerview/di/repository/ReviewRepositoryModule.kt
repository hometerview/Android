package com.ftw.hometerview.di.repository

import com.ftw.data.datasource.review.ReviewDataSource
import com.ftw.data.repository.review.ReviewRepositoryImpl
import com.ftw.domain.repository.review.ReviewRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ReviewRepositoryModule {

    @Provides
    @Singleton
    fun provideReviewRepository(reviewDataSource: ReviewDataSource): ReviewRepository {
        return ReviewRepositoryImpl(reviewDataSource)
    }
}
