package com.ftw.hometerview.ui.splash

import androidx.lifecycle.ViewModel
import com.ftw.domain.usecase.login.LoginUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SplashViewModel(
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

    init {
        _state.value = State.Loading
        CoroutineScope(dispatcher.ui()).launch {
            val result: Flow<Result<Boolean>> = loginUseCase.signIn()
            result.collect{
                if (it.isSuccess) {
                    _state.value = State.Success
                } else {
                   _state.value = State.Failure
                }
            }
        }
    }
}
