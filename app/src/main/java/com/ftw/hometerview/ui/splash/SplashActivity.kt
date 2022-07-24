package com.ftw.hometerview.ui.splash

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ftw.data.local.repository.login.ILoginDataStoreRepository
import com.ftw.domain.repository.login.LoginDataStoreRepository
import com.ftw.domain.usecase.LoginUseCase
import com.ftw.domain.usecase.LoginUseCaseImpl
import com.ftw.hometerview.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val _userToken = MutableLiveData<String>()
    private val userToken: LiveData<String>
        get() = _userToken

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var token: TextView

    private val loginDataStoreRepository: LoginDataStoreRepository = ILoginDataStoreRepository()
    private val loginUseCase: LoginUseCase = LoginUseCaseImpl(loginDataStoreRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        button1 = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)
        token = findViewById(R.id.textView)

        observeToken()

        button1.setOnClickListener {
            setUserToken("123")
        }

        button2.setOnClickListener {
            getUserToken()
        }


        /**
         * 1. 토큰 저장 여부 확인
         * 2. 토큰이 존재할 경우 로그인, 없다면 로그인 화면으로 이동
         *
         */

    }

    private fun getUserToken() {
        CoroutineScope(Dispatchers.IO).launch {
            loginUseCase.getUserToken()
                .collectLatest {
                    if(!it.isNullOrEmpty()) {
                        _userToken.postValue(it)
                    }else{
                        _userToken.postValue("Constants.DEFAULT_NAME")
                    }
                }
        }
    }

    private fun setUserToken(usertoken: String) {
        CoroutineScope(Dispatchers.IO).launch {
            loginUseCase.setUserToken(usertoken)
        }
    }

    private fun observeToken() {
        userToken.observe(this) {
            token.text = it
        }
    }
}
