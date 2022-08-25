package com.ftw.hometerview.di.ui

import com.ftw.domain.usecase.buildingreview.GetBuildingReviewsUseCase
import com.ftw.domain.usecase.login.LoginUseCase
import com.ftw.domain.usecase.review.GetLocationReviewsUseCase
import com.ftw.domain.usecase.searchaddressbuilding.GetSearchAddressBuildingUseCase
import com.ftw.domain.usecase.writtenreview.GetWrittenReviewsUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import com.ftw.hometerview.ui.buildingreview.BuildingReviewViewModel
import com.ftw.hometerview.ui.main.MainViewModel
import com.ftw.hometerview.ui.main.home.review.LocationReviewListViewModel
import com.ftw.hometerview.ui.searchaddressbuilding.SearchAddressBuildingViewModel
import com.ftw.hometerview.ui.searchcompanyresult.SearchCompanyResultViewModel
import com.ftw.hometerview.ui.splash.SplashViewModel
import com.ftw.hometerview.ui.updatenickname.UpdateNicknameViewModel
import com.ftw.hometerview.ui.writtenreview.WrittenReviewsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
class ActivityViewModelModule {
    @Provides
    @ActivityScoped
    fun provideSplashViewModel(
        dispatcher: Dispatcher,
        loginUseCase: LoginUseCase
    ): SplashViewModel {
        return SplashViewModel(dispatcher, loginUseCase)
    }

    @Provides
    @ActivityScoped
    fun provideSearchCompanyResultViewModel(
        dispatcher: Dispatcher
    ): SearchCompanyResultViewModel {
        return SearchCompanyResultViewModel(dispatcher)
    }

    @Provides
    @ActivityScoped
    fun provideMainViewModel(): MainViewModel {
        return MainViewModel()
    }

    @Provides
    @ActivityScoped
    @Named("BuildingList")
    fun provideHomeLocationReviewsViewModel(
        dispatcher: Dispatcher,
        getLocationReviewsUseCase: GetLocationReviewsUseCase
    ): LocationReviewListViewModel {
        return LocationReviewListViewModel(
            dispatcher,
            getLocationReviewsUseCase
        )
    }

    @Provides
    @ActivityScoped
    fun provideSearchAddressBuildingViewModel(
        dispatcher: Dispatcher,
        getSearchAddressBuildingUseCase: GetSearchAddressBuildingUseCase
    ): SearchAddressBuildingViewModel {
        return SearchAddressBuildingViewModel(
            dispatcher,
            getSearchAddressBuildingUseCase
        )
    }

    @Provides
    @ActivityScoped
    fun provideBuildingReviewViewModel(
        dispatcher: Dispatcher,
        getBuildingReviewsUseCase: GetBuildingReviewsUseCase
    ): BuildingReviewViewModel {
        return BuildingReviewViewModel(
            311,
            dispatcher,
            getBuildingReviewsUseCase
        )
    }

    @Provides
    @ActivityScoped
    fun provideUpdateNicknameViewModel(
        dispatcher: Dispatcher
    ): UpdateNicknameViewModel {
        return UpdateNicknameViewModel(
            dispatcher
        )
    }

    @Provides
    @ActivityScoped
    fun provideWrittenReviewsViewModel(
        dispatcher: Dispatcher,
        getWrittenReviewsUseCase: GetWrittenReviewsUseCase
    ): WrittenReviewsViewModel {
        return WrittenReviewsViewModel(
            dispatcher,
            getWrittenReviewsUseCase
        )
    }
}
