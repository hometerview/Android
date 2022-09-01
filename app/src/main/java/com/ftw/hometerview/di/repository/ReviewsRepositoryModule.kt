package com.ftw.hometerview.di.repository

import com.ftw.data.datasource.LoginDataSource
import com.ftw.data.datasource.ReviewsDataSource
import com.ftw.data.repository.review.ReviewsRepositoryImpl
import com.ftw.domain.repository.buildingreviews.ReviewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ReviewsRepositoryModule {

    @Provides
    @Singleton
    fun provideReviewsRepository(reviewsDataSource: ReviewsDataSource): ReviewsRepository {
        return ReviewsRepositoryImpl(reviewsDataSource)
    }
}
