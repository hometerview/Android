package com.ftw.hometerview.ui.login

import androidx.lifecycle.ViewModel
import com.ftw.domain.entity.KakaoToken
import com.ftw.domain.usecase.login.LoginSignUpUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val dispatcher: Dispatcher,
    private val loginSignUpUseCase: LoginSignUpUseCase
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
            val result: Result<Boolean> = loginSignUpUseCase.signUp(kakaoToken)
            if(result.isSuccess){
                _state.value = State.Success
            } else {
                _state.value = State.Failure
            }
        }
    }


}
