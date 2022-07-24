package com.ftw.hometerview.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ftw.domain.usecase.LoginUseCase
import com.ftw.domain.usecase.LoginUseCaseImpl
import com.ftw.hometerview.R
import com.kakao.sdk.common.util.Utility


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val useCase: LoginUseCase = LoginUseCaseImpl()
//        Log.d("MainActivity123123", "keyHash: ${Utility.getKeyHash(this)}")

    }
}
