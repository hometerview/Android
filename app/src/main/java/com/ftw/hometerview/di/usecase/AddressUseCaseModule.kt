package com.ftw.hometerview.di.usecase

import com.ftw.domain.usecase.address.GetAddressUseCase
import com.ftw.domain.usecase.address.GetAddressUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AddressUseCaseModule {

    @Provides
    @Singleton
    fun provideGetAddressUseCase(): GetAddressUseCase {
        return GetAddressUseCaseImpl()
    }
}
