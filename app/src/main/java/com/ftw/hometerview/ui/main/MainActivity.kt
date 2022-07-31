package com.ftw.hometerview.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ftw.domain.usecase.login.LoginUseCase
import com.ftw.domain.usecase.login.LoginUseCaseImpl
import com.ftw.hometerview.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
