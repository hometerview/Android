package com.ftw.hometerview.ui.splash

import androidx.lifecycle.ViewModel
import com.ftw.domain.usecase.login.LoginUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        CoroutineScope(dispatcher.ui()).launch {
            val result: Flow<Result<String>> = withContext(dispatcher.io()) {
                loginUseCase()
            }

            result.collect {
                if (it.isSuccess) _state.value = State.Success
                else _state.value = State.Failure
            }
        }
    }
}
