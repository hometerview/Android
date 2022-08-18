package com.ftw.hometerview.di.ui

import com.ftw.domain.usecase.map.GetBuildingMarkerUseCase
import com.ftw.domain.usecase.map.GetMarkerUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import com.ftw.hometerview.ui.main.map.MapViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class MapFragmentViewModelModule {

    @Provides
    @FragmentScoped
    fun provideMapViewModel(
        dispatcher: Dispatcher,
        getMarkerUseCase: GetMarkerUseCase,
        getBuildingMarkerUseCase: GetBuildingMarkerUseCase
    ): MapViewModel {
        return MapViewModel(
            dispatcher,
            getMarkerUseCase,
            getBuildingMarkerUseCase
        )
    }

}
