package com.ftw.hometerview.di.ui

import com.ftw.domain.usecase.search.GetSearchedBuildingAddressesUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import com.ftw.hometerview.ui.review.first.CreateReviewFirstStepInputAddressViewModel
import com.ftw.hometerview.ui.review.first.CreateReviewFirstStepSelectFloorViewModel
import com.ftw.hometerview.ui.review.second.CreateReviewSecondStepReviewViewModel
import com.ftw.hometerview.ui.review.third.CreateReviewThirdStepSearchCompanyViewModel
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
        getSearchedBuildingAddressesUseCase: GetSearchedBuildingAddressesUseCase
    ): CreateReviewFirstStepInputAddressViewModel {
        return CreateReviewFirstStepInputAddressViewModel(
            dispatcher,
            getSearchedBuildingAddressesUseCase
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

    @Provides
    @FragmentScoped
    fun provideCreateReviewThirdStepReviewViewModel(): CreateReviewThirdStepSearchCompanyViewModel {
        return CreateReviewThirdStepSearchCompanyViewModel()
    }
}
