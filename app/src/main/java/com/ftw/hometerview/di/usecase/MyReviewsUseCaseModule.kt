package com.ftw.hometerview.di.usecase

import com.ftw.domain.usecase.myreviews.GetMyReviewsUseCase
import com.ftw.domain.usecase.myreviews.GetMyReviewsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MyReviewsUseCaseModule {
    @Provides
    @Singleton
    fun provideGetMyReviewsUseCase(): GetMyReviewsUseCase {
        return GetMyReviewsUseCaseImpl()
    }
}
