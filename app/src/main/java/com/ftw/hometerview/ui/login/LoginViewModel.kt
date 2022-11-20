package com.ftw.hometerview.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ftw.domain.entity.KakaoToken
import com.ftw.domain.usecase.login.LoginUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val dispatcher: Dispatcher,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    sealed class State {
        object Success : State()
        object Failure : State()
        object Loading : State()
    }

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> get() = _state.asStateFlow()

    //등록된 회원인지 아닌지 여부랑 아니면 등록까지 시켜주기
    fun setKakaoToken(kakaoToken: KakaoToken){
        _state.value = State.Loading
        CoroutineScope(dispatcher.ui()).launch {
            val result: Result<Boolean> = loginUseCase.signUp(kakaoToken)
            Log.d("adasdas",result.toString())
            if(result.isSuccess){
                _state.value = State.Success
            } else {
                _state.value = State.Failure
            }
        }
    }


}