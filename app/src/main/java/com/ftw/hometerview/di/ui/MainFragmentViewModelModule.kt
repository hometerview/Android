package com.ftw.hometerview.di.ui

import com.ftw.domain.usecase.favorite.GetFavoriteBuildingsUseCase
import com.ftw.domain.usecase.favorite.GetFavoriteReviewsUseCase
import com.ftw.domain.usecase.review.GetLocationReviewsUseCase
import com.ftw.domain.usecase.user.GetCachedUserUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import com.ftw.hometerview.ui.main.favorite.favoritelist.FavoriteBuildingsViewModel
import com.ftw.hometerview.ui.main.favorite.favoritelist.FavoriteReviewsViewModel
import com.ftw.hometerview.ui.main.home.HomeViewModel
import com.ftw.hometerview.ui.main.home.review.LocationReviewListViewModel
import com.ftw.hometerview.ui.main.mypage.MyPageViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Named

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
    @Named("LocationReviewList")
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
    @FragmentScoped
    fun provideUpdateNicknameViewModel(
        dispatcher: Dispatcher,
        getCachedUserUseCase: GetCachedUserUseCase
    ): MyPageViewModel {
        return MyPageViewModel(
            dispatcher,
            getCachedUserUseCase
        )
    }


    @Provides
    @FragmentScoped
    fun provideFavoriteBuildingsViewModel(
        dispatcher: Dispatcher,
        getFavoriteBuildingsUseCase: GetFavoriteBuildingsUseCase
    ): FavoriteBuildingsViewModel {
        return FavoriteBuildingsViewModel(
            dispatcher,
            getFavoriteBuildingsUseCase
        )
    }

    @Provides
    @FragmentScoped
    fun provideFavoriteReviewsViewModel(
        dispatcher: Dispatcher,
        getFavoriteReviewsUseCase: GetFavoriteReviewsUseCase
    ): FavoriteReviewsViewModel {
        return FavoriteReviewsViewModel(
            dispatcher,
            getFavoriteReviewsUseCase
        )
    }
}
