package com.ftw.hometerview.di.api

import com.ftw.data.remote.api.BuildingReviewsAPI
import com.ftw.data.remote.api.SignUpAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class APIModule {

    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit): SignUpAPI {
        return retrofit.create(SignUpAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideBuildingReviewsApi(retrofit: Retrofit): BuildingReviewsAPI {
        return retrofit.create(BuildingReviewsAPI::class.java)
    }
}
