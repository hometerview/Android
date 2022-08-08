package com.ftw.hometerview.di.ui

import com.ftw.domain.usecase.review.GetLocaionReviewsUseCase
import com.ftw.domain.usecase.user.GetCachedUserUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import com.ftw.hometerview.ui.main.home.HomeViewModel
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
        getLocationReviewsUseCase: GetLocaionReviewsUseCase
    ): HomeViewModel {
        return HomeViewModel(
            dispatcher,
            getCachedUserUseCase,
            getLocationReviewsUseCase
        )
    }
}
