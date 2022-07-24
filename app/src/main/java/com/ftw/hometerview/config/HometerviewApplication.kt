package com.ftw.hometerview.config

import android.app.Application
import com.ftw.data.local.datasource.local.DataStoreProvider
import com.ftw.hometerview.R
import com.kakao.sdk.common.KakaoSdk

class HometerviewApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DataStoreProvider.init(this)

        KakaoSdk.init(this, applicationContext.getString(R.string.kakao_api_key))
    }

}
