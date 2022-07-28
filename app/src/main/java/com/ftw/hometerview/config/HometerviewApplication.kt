package com.ftw.hometerview.config

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import com.ftw.hometerview.BuildConfig
import com.ftw.hometerview.R
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HometerviewApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKakaoSdk()
        initializeFlipper()
    }

    private fun initializeKakaoSdk() {
        KakaoSdk.init(this, getString(R.string.kakao_api_key))
    }

    private fun initializeFlipper() {
        SoLoader.init(this, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            AndroidFlipperClient.getInstance(this).apply {
                addPlugin(InspectorFlipperPlugin(this@HometerviewApplication, DescriptorMapping.withDefaults()))
                addPlugin(NetworkFlipperPlugin())
            }.run { start() }
        }
    }
}
