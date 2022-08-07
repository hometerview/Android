package com.ftw.hometerview.ui.main.home.review

import com.ftw.domain.entity.Review
import com.ftw.domain.usecase.review.GetLocationReviewsUseCase
import com.ftw.hometerview.BR
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.RecyclerItem
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transformLatest

class LocationReviewListViewModel(
    dispatcher: Dispatcher,
    getLocationReviewsUseCase: GetLocationReviewsUseCase
) {
    sealed class State {
        object Loading : State()
        class OnClickReview(val buildingId: Long) : State()
    }

    private val location: MutableStateFlow<String> = MutableStateFlow("")

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    val reviews: StateFlow<List<RecyclerItem>> = location.filter { it.isNotBlank() }
        .transformLatest { location ->
            flow<List<RecyclerItem>> {
                emit(
                    getLocationReviewsUseCase(location)
                        .flatMap { it.reviews }
                        .map { review ->
                            RecyclerItem(
                                data = LocationReviewItem(
                                    onClickItem = { buildingId ->
                                        _state.value = State.OnClickReview(buildingId)
                                    },
                                    review = review
                                ),
                                layoutId = R.layout.list_item_location_review,
                                variableId = BR.item
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

data class LocationReviewItem(
    val onClickItem: (buildingId: Long) -> Unit,
    val review: Review
) {

    fun onItemClick() {
        onClickItem(review.buildingId)
    }
}
