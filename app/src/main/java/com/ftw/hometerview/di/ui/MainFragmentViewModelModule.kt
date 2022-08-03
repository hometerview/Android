package com.ftw.hometerview.di.ui

import com.ftw.domain.usecase.review.GetLocationReviewsUseCase
import com.ftw.domain.usecase.user.GetCachedUserUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import com.ftw.hometerview.ui.main.home.HomeViewModel
import com.ftw.hometerview.ui.main.home.review.LocationReviewListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class MainFragmentViewModelModule {

    @Provides
    @FragmentScoped
    fun provideHomeViewModel(
        dispatcher: Dispatcher,
        getCachedUserUseCase: GetCachedUserUseCase,
        getLocationReviewsUseCase: GetLocationReviewsUseCase
    ): HomeViewModel {
        return HomeViewModel(
            dispatcher,
            getCachedUserUseCase,
            getLocationReviewsUseCase
        )
    }

    @Provides
    @FragmentScoped
    fun provideHomeLocationReviewsViewModel(
        dispatcher: Dispatcher,
        getLocationReviewsUseCase: GetLocationReviewsUseCase
    ): LocationReviewListViewModel {
        return LocationReviewListViewModel(
            dispatcher,
            getLocationReviewsUseCase
        )
    }
}
