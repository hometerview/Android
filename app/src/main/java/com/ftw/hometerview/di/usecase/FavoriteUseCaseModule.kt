package com.ftw.hometerview.di.usecase

import com.ftw.domain.usecase.favorite.GetFavoriteBuildingsUseCase
import com.ftw.domain.usecase.favorite.GetFavoriteBuildingsUseCaseImpl
import com.ftw.domain.usecase.favorite.GetFavoriteReviewsUseCase
import com.ftw.domain.usecase.favorite.GetFavoriteReviewsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FavoriteUseCaseModule {
    @Provides
    @Singleton
    fun provideGetFavoriteReviewsUseCase(): GetFavoriteReviewsUseCase {
        return GetFavoriteReviewsUseCaseImpl()
    }

    @Provides
    @Singleton
    fun provideGetFavoriteBuildingUseCase(): GetFavoriteBuildingsUseCase {
        return GetFavoriteBuildingsUseCaseImpl()
    }

}
