package com.ftw.hometerview.ui.review.second

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateReviewSecondStepViewModel {

    sealed class Event {
        object Nothing : Event()
        object OnClickResidentialFloor : Event()
        class OnClickNext(val address: String, val floor: String) : Event()
    }

    private val _event: MutableStateFlow<Event> = MutableStateFlow(Event.Nothing)
    val event: StateFlow<Event> = _event.asStateFlow()

    private val _address: MutableStateFlow<String> = MutableStateFlow("")
    val address: StateFlow<String> = _address.asStateFlow()

    val residentialFloor: MutableStateFlow<String> = MutableStateFlow("")

    fun setAddress(address: String) {
        _address.value = address
    }

    fun setResidentialFloor(residentialFloor: String) {
        this.residentialFloor.value = residentialFloor
    }

    fun onClickResidentialFloor() {
        _event.value = Event.OnClickResidentialFloor
        _event.value = Event.Nothing
    }

    fun onClickNext() {
        _event.value = Event.OnClickNext(address.value, residentialFloor.value)
        _event.value = Event.Nothing
    }
}
