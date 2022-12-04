package com.ftw.hometerview.ui.review.second

import java.util.Date
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateReviewSecondStepReviewViewModel {

    sealed class State {
        object None : State()
        class OnClickResidentialPeriod(val periods: List<Int>) : State()
        object OnClickBack : State()
        class OnClickNext(
            val rating: Int,
            val leftAt: Date,
            val advantage: String,
            val disadvantage: String
        ) : State()
    }

    private val _event: MutableStateFlow<State> = MutableStateFlow(State.None)
    val event: StateFlow<State> = _event.asStateFlow()

    private val _starGuide: MutableStateFlow<String> = MutableStateFlow("")
    val starGuide: StateFlow<String> = _starGuide.asStateFlow()

    val rating: MutableStateFlow<Float> = MutableStateFlow(0F)

    val residentialPeriod: MutableStateFlow<String> = MutableStateFlow("")

    var advantage: MutableStateFlow<String> = MutableStateFlow("")
    val disadvantage: MutableStateFlow<String> = MutableStateFlow("")

    fun onClickResidentialPeriod() {
        _event.value = State.OnClickResidentialPeriod(
            (2022 downTo 2018).toList()
        )
    }

    fun onClickBack() {
        _event.value = State.OnClickBack
    }

    fun onClickNext() {
        _event.value = State.OnClickNext(
            rating.value.toInt(),
            Date(),
            advantage.value,
            disadvantage.value
        )
    }
}
