package com.ftw.hometerview.di.datasource

import com.ftw.data.datasource.ReviewsDataSource
import com.ftw.data.network.datasource.ReviewsRemoteDataSource
import com.ftw.data.remote.api.BuildingReviewsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ReviewsDataSourceModule {

    @Provides
    @Singleton
    fun provideTokenDataSource(buildingReviewsAPI: BuildingReviewsAPI): ReviewsDataSource {
        return ReviewsRemoteDataSource(buildingReviewsAPI)
    }
}
