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

    sealed class Event {
        object None : Event()
        class OnClickUpdateNickname(val nickname: String) : Event()
        object OnClickWrittenReview : Event()
        object OnClickManageAccount : Event()
        object OnClickLogout : Event()
    }

    private val _user: MutableStateFlow<User> = MutableStateFlow(User.NONE)
    val user: StateFlow<User> = _user.asStateFlow()

    private val _event: MutableStateFlow<Event> = MutableStateFlow(Event.None)
    val event: StateFlow<Event> = _event.asStateFlow()

    val showPopup: MutableStateFlow<Boolean> = MutableStateFlow(false)

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

    fun onClickUpdateNickname() {
        _event.value = Event.OnClickUpdateNickname(user.value.nickName)
        _event.value = Event.None
    }

    fun onClickWrittenReview() {
        _event.value = Event.OnClickWrittenReview
        _event.value = Event.None
    }

    fun onClickManageAccount() {
        _event.value = Event.OnClickManageAccount
        _event.value = Event.None
    }

    fun onClickLogout() {
        _event.value = Event.OnClickLogout
        _event.value = Event.None
    }
}
