package com.ftw.hometerview.ui.manageaccount

import kotlinx.coroutines.flow.*

class ManageAccountViewModel() {

    sealed class Event {
        object None : Event()
        object onClickServiceUseTerms : Event()
        object onClickUseUserInfo : Event()
        object onClickLocationForService : Event()
        object onClickOpenSourceLibrary : Event()
        object onClickWithdrawal : Event()
    }

    private val _event: MutableStateFlow<Event> = MutableStateFlow(Event.None)
    val event: StateFlow<Event> = _event.asStateFlow()

    fun onClickServiceUseTerms() {
        _event.value = Event.onClickServiceUseTerms
        _event.value = Event.None
    }
    fun onClickUseUserInfo() {
        _event.value = Event.onClickUseUserInfo
        _event.value = Event.None
    }
    fun onClickLocationForService() {
        _event.value = Event.onClickLocationForService
        _event.value = Event.None
    }
    fun onClickOpenSourceLibrary() {
        _event.value = Event.onClickOpenSourceLibrary
        _event.value = Event.None
    }
    fun onClickWithdrawal() {
        _event.value = Event.onClickWithdrawal
        _event.value = Event.None
    }
}
