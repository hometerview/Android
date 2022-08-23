package com.ftw.hometerview.di.usecase

import com.ftw.domain.usecase.writtenreview.GetWrittenReviewsUseCase
import com.ftw.domain.usecase.writtenreview.GetWrittenReviewsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WrittenReviewsUseCaseModule {
    @Provides
    @Singleton
    fun provideGetWrittenReviewsUseCase(): GetWrittenReviewsUseCase {
        return GetWrittenReviewsUseCaseImpl()
    }
}
