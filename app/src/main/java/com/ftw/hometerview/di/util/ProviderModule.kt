package com.ftw.hometerview.di.util

import android.content.Context
import com.ftw.data.local.datastore.DataStoreProvider
import com.ftw.hometerview.config.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProviderModule {

    @Provides
    @Singleton
    fun provideDataStoreProvider(@ApplicationContext context: Context): DataStoreProvider {
        return DataStoreProvider(context.dataStore)
    }
}
