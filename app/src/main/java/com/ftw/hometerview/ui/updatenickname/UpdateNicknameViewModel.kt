package com.ftw.hometerview.ui.updatenickname

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

class UpdateNicknameViewModel(
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
