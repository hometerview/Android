package com.ftw.hometerview.di.api

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.ftw.hometerview.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private const val DEBUG_BASE_URL = "http://3.36.37.15:8080/"
        private const val RELEASE_BASE_URL = "http://3.36.37.15:8080/"
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            it.proceed(
                it.request()
                    .newBuilder()
                    .addHeader("Authorization", "token")
                    .build()
            )
        }
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideNetworkFlipperPlugin(): NetworkFlipperPlugin {
        return NetworkFlipperPlugin()
    }

    @Provides
    @Singleton
    fun provideFlipperInterceptor(networkFlipperPlugin: NetworkFlipperPlugin): FlipperOkhttpInterceptor {
        return FlipperOkhttpInterceptor(networkFlipperPlugin)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        networkFlipperOkhttpInterceptor: FlipperOkhttpInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(networkFlipperOkhttpInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val baseUrl = if (BuildConfig.DEBUG) DEBUG_BASE_URL else RELEASE_BASE_URL
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
