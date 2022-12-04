package com.ftw.hometerview.di.api

import com.ftw.data.remote.api.BuildingReviewsAPI
import com.ftw.data.remote.api.LoginAPI
import com.ftw.data.remote.api.SearchAPI
import com.ftw.data.remote.api.review.ReviewAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class APIModule {

    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit): LoginAPI {
        return retrofit.create(LoginAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideReviewApi(retrofit: Retrofit): ReviewAPI {
        return retrofit.create(ReviewAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideBuildingReviewsApi(retrofit: Retrofit): BuildingReviewsAPI {
        return retrofit.create(BuildingReviewsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchApi(retrofit: Retrofit): SearchAPI {
        return retrofit.create(SearchAPI::class.java)
    }
}
