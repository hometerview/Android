package com.ftw.hometerview.ui.updatenickname

import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UpdateNicknameViewModel(
    dispatcher: Dispatcher
) {

    sealed class Event {
        object None : Event()
        class OnClickNext(val nickname: String) : Event()
    }

    private val _event: MutableStateFlow<Event> = MutableStateFlow(Event.None)
    val event: StateFlow<Event> = _event.asStateFlow()

    val nickname: MutableStateFlow<String> = MutableStateFlow("")


    fun onClickUpdateComplete() {
        _event.value = Event.OnClickNext(nickname.value)
        _event.value = Event.None
    }
}
