package com.ftw.hometerview.di.repository

import com.ftw.data.datasource.BuildingReviewsDataSource
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
    fun provideReviewsRepository(buildingReviewsDataSource: BuildingReviewsDataSource): ReviewsRepository {
        return ReviewsRepositoryImpl(buildingReviewsDataSource)
    }
}
