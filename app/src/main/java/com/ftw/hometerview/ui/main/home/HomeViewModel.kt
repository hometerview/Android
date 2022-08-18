package com.ftw.hometerview.ui.main.home

import com.ftw.domain.entity.Company
import com.ftw.domain.entity.LocationReview
import com.ftw.domain.entity.User
import com.ftw.domain.usecase.review.GetLocationReviewsUseCase
import com.ftw.domain.usecase.user.GetCachedUserUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class HomeViewModel(
    dispatcher: Dispatcher,
    private val getCachedUserUseCase: GetCachedUserUseCase,
    private val getLocationReviewsUseCase: GetLocationReviewsUseCase
) {
    sealed class State {
        object None : State()
        class Error(val message: String) : State()
    }

    private val _user: MutableStateFlow<User> = MutableStateFlow(User.NONE)
    val user: StateFlow<User> = _user.asStateFlow()

    private val _reviews: MutableStateFlow<List<LocationReview>> = MutableStateFlow(emptyList())
    val reviews: StateFlow<List<LocationReview>> = _reviews.asStateFlow()

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.None)
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        CoroutineScope(dispatcher.ui()).launch {
            flow {
                emit(getCachedUserUseCase())
            }
                .catch { emit(User(Company.NONE)) }
                .collect {
                    _user.value = it
                }

            flow {
                emit(getLocationReviewsUseCase(user.value.company?.location ?: "역삼역"))
            }
                .catch { exception -> _state.value = State.Error(exception.message ?: "") }
                .collect {
                    _reviews.value = it
                }
        }
    }
}
