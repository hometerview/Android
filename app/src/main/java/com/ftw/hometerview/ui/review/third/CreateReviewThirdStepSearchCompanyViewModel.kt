package com.ftw.hometerview.ui.review.third

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateReviewThirdStepSearchCompanyViewModel {
    sealed class Event {
        object None : Event()
        object OnClickSearchCompany : Event()
        class OnClickNext(val company: String) : Event()
    }

    private val _event: MutableStateFlow<Event> = MutableStateFlow(Event.None)
    val event: StateFlow<Event> = _event.asStateFlow()

    val company: MutableStateFlow<String> = MutableStateFlow("삼성전자")

    fun onClickSearchCompany() {
        _event.value = Event.OnClickSearchCompany
        _event.value = Event.None
    }

    fun onClickNext() {
        _event.value = Event.OnClickNext(company.value)
        _event.value = Event.None
    }
}
