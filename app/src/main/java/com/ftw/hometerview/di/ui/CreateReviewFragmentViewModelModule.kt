package com.ftw.hometerview.di.ui

import com.ftw.domain.usecase.address.GetAddressUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import com.ftw.hometerview.ui.review.first.CreateReviewFirstStepViewModel
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
    fun provideCreateReviewFirstStepViewModel(
        dispatcher: Dispatcher,
        getAddressUseCase: GetAddressUseCase
    ): CreateReviewFirstStepViewModel {
        return CreateReviewFirstStepViewModel(
            dispatcher,
            getAddressUseCase
        )
    }
}
