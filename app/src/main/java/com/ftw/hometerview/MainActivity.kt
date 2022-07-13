package com.ftw.hometerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ftw.domain.usecase.LoginUseCase
import com.ftw.domain.usecase.LoginUseCaseImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val useCase: LoginUseCase = LoginUseCaseImpl()
        Log.d("MainActivity123123", "useCase: ${useCase()}")
    }
}