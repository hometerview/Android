package com.ftw.hometerview.ui.buildingreview

import com.ftw.domain.entity.Building
import com.ftw.domain.entity.Review
import com.ftw.domain.entity.TestReview
import com.ftw.domain.usecase.buildingreviews.GetBuildingUseCase
import com.ftw.domain.usecase.buildingreviews.GetReviewsUseCase
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
    private val buildingId: String,
    private val dispatcher: Dispatcher,
    private val getLocationReviewsUseCase: GetReviewsUseCase,
    private val getBuildingUseCase: GetBuildingUseCase
) {

    sealed class State {
        object Loading : State()
        class OnClickReview(buildingId: String) : State()
    }

    private val _building: MutableStateFlow<Building> = MutableStateFlow(Building.NONE)
    val building: StateFlow<Building> = _building.asStateFlow()

    private val _reviews: MutableStateFlow<List<TestReview>> = MutableStateFlow(emptyList())
    val reviews: StateFlow<List<TestReview>> = _reviews.asStateFlow()

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
            val review = withContext(dispatcher.io()) {
                getLocationReviewsUseCase(buildingId)
            }
            val building = withContext(dispatcher.io()) {
                getBuildingUseCase(buildingId)
            }

            _building.value = building
            if (review != null) {
                _reviews.value = review
            }
        }
    }
}

data class BuildingReviewItem(
    val onClickItem: (buildingId: String) -> Unit,
    val review: TestReview
)
