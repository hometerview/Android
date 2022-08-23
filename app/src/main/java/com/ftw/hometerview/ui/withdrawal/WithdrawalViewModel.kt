package com.ftw.hometerview.ui.withdrawal

import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WithdrawalViewModel(
    dispatcher: Dispatcher
) {

    sealed class Event {
        object None : Event()
        object OnClickWithdrawalFirstCheck : Event()
        object OnClickWithdrawalSecondCheck : Event()
        object OnClickWithdrawalThirdCheck : Event()
    }

    private val _event: MutableStateFlow<Event> = MutableStateFlow(Event.None)
    val event: StateFlow<Event> = _event.asStateFlow()

    val withdrawalFirstCheck: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val withdrawalSecondCheck: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val withdrawalThirdCheck: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val withdrawalCheck: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun onClickWithdrawalFirstCheck() {
        _event.value = Event.OnClickWithdrawalFirstCheck
        _event.value = Event.None
    }

    fun onClickWithdrawalSecondCheck() {
        _event.value = Event.OnClickWithdrawalSecondCheck
        _event.value = Event.None
    }

    fun onClickWithdrawalThirdCheck() {
        _event.value = Event.OnClickWithdrawalThirdCheck
        _event.value = Event.None
    }

}
