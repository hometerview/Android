package com.ftw.hometerview.ui.buildingreview

import com.ftw.domain.entity.Building
import com.ftw.domain.entity.Review
import com.ftw.domain.usecase.buildingreview.GetBuildingReviewsUseCase
import com.ftw.hometerview.BR
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.RecyclerItem
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BuildingReviewViewModel(
    private val buildingId: Long,
    private val dispatcher: Dispatcher,
    private val getLocationReviewsUseCase: GetBuildingReviewsUseCase
) {

    sealed class State {
        object Loading : State()
        class OnClickReview(buildingId: Long) : State()
    }

    private val _building: MutableStateFlow<Building> = MutableStateFlow(Building.NONE)
    val building: StateFlow<Building> = _building.asStateFlow()

    private val _reviews: MutableStateFlow<List<Review>> = MutableStateFlow(emptyList())
    val reviews: StateFlow<List<Review>> = _reviews.asStateFlow()

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    val time: StateFlow<String> = MutableStateFlow("약 1시간 30분")

    val items: StateFlow<List<RecyclerItem>> = reviews.transformLatest { reviews ->
        flowOf(
            reviews.map { review ->
                RecyclerItem(
                    data = BuildingReviewItem(
                        onClickItem = { buildingId ->
                            _state.value = State.OnClickReview(buildingId)
                        },
                        review = review
                    ),
                    layoutId = R.layout.list_item_building_review,
                    variableId = BR.item
                )
            }
        ).collect {
            emit(it)
        }
    }.stateIn(CoroutineScope(dispatcher.ui()), SharingStarted.Eagerly, emptyList())

    init {
        CoroutineScope(dispatcher.ui()).launch {
            val buildingReview = withContext(dispatcher.io()) {
                getLocationReviewsUseCase(buildingId)
            }

            _building.value = buildingReview.building
            _reviews.value = buildingReview.reviews
        }
    }
}

data class BuildingReviewItem(
    val onClickItem: (buildingId: Long) -> Unit,
    val review: Review
)
