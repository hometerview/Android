package com.ftw.hometerview.di.datasource

import com.ftw.data.datasource.review.ReviewDataSource
import com.ftw.data.remote.api.review.ReviewAPI
import com.ftw.data.remote.datasource.review.ReviewRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ReviewDataSourceModule {

    @Provides
    @Singleton
    fun provideReviewDataSource(api: ReviewAPI): ReviewDataSource {
        return ReviewRemoteDataSource(api)
    }
}
