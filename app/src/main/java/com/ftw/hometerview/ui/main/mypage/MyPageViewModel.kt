package com.ftw.hometerview.ui.main.mypage

import com.ftw.domain.entity.Company
import com.ftw.domain.entity.User
import com.ftw.domain.usecase.user.GetCachedUserUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MyPageViewModel (
    dispatcher: Dispatcher,
    private val getCachedUserUseCase: GetCachedUserUseCase
) {

    private val _user: MutableStateFlow<User> = MutableStateFlow(User.NONE)
    val user: StateFlow<User> = _user.asStateFlow()

    init {
        CoroutineScope(dispatcher.ui()).launch {
            flow {
                emit(getCachedUserUseCase())
            }
                .catch { emit(User("", Company.NONE)) }
                .collect {
                    _user.value = it
                }

        }
    }
}