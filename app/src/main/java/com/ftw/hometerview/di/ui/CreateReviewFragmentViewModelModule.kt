package com.ftw.hometerview.di.ui

import com.ftw.domain.usecase.address.GetAddressUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import com.ftw.hometerview.ui.review.first.CreateReviewFirstStepInputAddressViewModel
import com.ftw.hometerview.ui.review.first.CreateReviewFirstStepSelectFloorViewModel
import com.ftw.hometerview.ui.review.second.CreateReviewSecondStepReviewViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class CreateReviewFragmentViewModelModule {

    @Provides
    @FragmentScoped
    fun provideCreateReviewFirstStepInputAddressViewModel(
        dispatcher: Dispatcher,
        getAddressUseCase: GetAddressUseCase
    ): CreateReviewFirstStepInputAddressViewModel {
        return CreateReviewFirstStepInputAddressViewModel(
            dispatcher,
            getAddressUseCase
        )
    }

    @Provides
    @FragmentScoped
    fun provideCreateReviewFirstStepSelectFloorViewModel(): CreateReviewFirstStepSelectFloorViewModel {
        return CreateReviewFirstStepSelectFloorViewModel()
    }

    @Provides
    @FragmentScoped
    fun provideCreateReviewSecondStepReviewViewModel(): CreateReviewSecondStepReviewViewModel {
        return CreateReviewSecondStepReviewViewModel()
    }
}
