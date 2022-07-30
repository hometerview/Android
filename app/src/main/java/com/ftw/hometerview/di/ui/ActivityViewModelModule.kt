package com.ftw.hometerview.di.ui

import com.ftw.domain.usecase.login.LoginUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import com.ftw.hometerview.ui.onboardingresult.OnboardingResultViewModel
import com.ftw.hometerview.ui.splash.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

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
    fun provideOnboardResultViewModel(
        dispatcher: Dispatcher
    ): OnboardingResultViewModel {
        return OnboardingResultViewModel(dispatcher)
    }
}
