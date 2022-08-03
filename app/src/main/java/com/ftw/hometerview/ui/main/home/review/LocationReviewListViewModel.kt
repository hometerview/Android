package com.ftw.hometerview.ui.main.home.review

import com.ftw.domain.usecase.review.GetLocationReviewsUseCase
import com.ftw.hometerview.BR
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.RecyclerItem
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transformLatest

class LocationReviewListViewModel(
    dispatcher: Dispatcher,
    getLocationReviewsUseCase: GetLocationReviewsUseCase
) {

    private val location: MutableStateFlow<String> = MutableStateFlow("")

    val reviews: StateFlow<List<RecyclerItem>> = location.filter { it.isNotBlank() }
        .transformLatest { location ->
            flow<List<RecyclerItem>> {
                emit(
                    getLocationReviewsUseCase(location)
                        .flatMap { it.reviews }
                        .map { review ->
                            RecyclerItem(
                                data = review,
                                layoutId = R.layout.list_item_location_review,
                                variableId = BR.review
                            )
                        }
                )
            }
                .collect {
                    emit(it)
                }
        }
        .stateIn(CoroutineScope(dispatcher.ui()), SharingStarted.Eagerly, emptyList())

    fun setLocation(location: String) {
        this.location.value = location
    }
}
