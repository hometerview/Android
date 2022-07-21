package com.ftw.hometerview.config

import android.app.Application
import com.ftw.hometerview.R
import com.kakao.sdk.common.KakaoSdk

class HometerviewApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, applicationContext.getString(R.string.kakao_api_key))
    }

}
