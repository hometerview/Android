package com.ftw.hometerview.config

import android.app.Application
import com.ftw.hometerview.R
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HometerviewApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, getString(R.string.kakao_api_key))
    }
}
