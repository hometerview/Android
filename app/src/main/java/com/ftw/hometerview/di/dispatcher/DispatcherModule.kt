package com.ftw.hometerview.di.dispatcher

import com.ftw.hometerview.dispatcher.AppDispatcher
import com.ftw.hometerview.dispatcher.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {

    @Provides
    @Singleton
    fun provideDispatcher(): Dispatcher = AppDispatcher()
}
