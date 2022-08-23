package com.ftw.hometerview.ui.writtenreview.nonreview

import kotlinx.coroutines.flow.*

class NonReviewViewModel() {

    sealed class Event {
        object None : Event()
        object onClickWriteReview : Event()
    }

    private val _event: MutableStateFlow<Event> = MutableStateFlow(Event.None)
    val event: StateFlow<Event> = _event.asStateFlow()

    fun onClickWriteReview() {
        _event.value = Event.onClickWriteReview
        _event.value = Event.None
    }

}