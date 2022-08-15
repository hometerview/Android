package com.ftw.hometerview.ui.main.favorite.favoritelist

import com.ftw.domain.entity.FavoriteBuilding
import com.ftw.domain.entity.FavoriteReview
import com.ftw.domain.usecase.favorite.GetFavoriteBuildingUseCase
import com.ftw.domain.usecase.favorite.GetFavoriteReviewsUseCase
import com.ftw.hometerview.BR
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.RecyclerItem
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class FavoriteListViewModel(
    dispatcher: Dispatcher,
    private val getFavoriteBuildingUseCase: GetFavoriteBuildingUseCase,
    private val getFavoriteReviewsUseCase: GetFavoriteReviewsUseCase
) {

    sealed class State {
        object Loading : State()
        class OnClickReview(val buildingId: Long) : State()
    }

    private val _state: MutableStateFlow<State> = MutableStateFlow(
        State.Loading
    )
    val state: StateFlow<State> = _state.asStateFlow()

    private val _savedReviews: MutableStateFlow<List<FavoriteReview>> =
        MutableStateFlow(emptyList())
    val savedReviews: StateFlow<List<FavoriteReview>> = _savedReviews.asStateFlow()

    private val _savedBuildings: MutableStateFlow<List<FavoriteBuilding>> =
        MutableStateFlow(emptyList())
    val savedBuildings: StateFlow<List<FavoriteBuilding>> = _savedBuildings.asStateFlow()


    val savedReviewsItems: StateFlow<List<RecyclerItem>> =
        savedReviews.transformLatest { location ->
            flow {
                emit(
                    getFavoriteReviewsUseCase()
                        .map { reviews ->
                            RecyclerItem(
                                data = SavedReviewItem(
                                    onClickItem = { buildingId ->
                                        _state.value = State.OnClickReview(buildingId)
                                    },
                                    savedReviews = reviews
                                ),
                                layoutId = R.layout.list_item_favorite_review,
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


    val savedBuildingsItems: StateFlow<List<RecyclerItem>> =
        savedBuildings.transformLatest { location ->
            flow {
                emit(
                    getFavoriteBuildingUseCase()
                        .map { building ->
                            RecyclerItem(
                                data = SavedBuildingItem(
                                    onClickItem = { buildingId ->
                                        _state.value = State.OnClickReview(buildingId)
                                    },
                                    savedBuildings = building
                                ),
                                layoutId = R.layout.list_item_favorite_building,
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

}

data class SavedReviewItem(
    val onClickItem: (buildingId: Long) -> Unit,
    val savedReviews: FavoriteReview
) {

    fun onItemClick() {
        onClickItem(savedReviews.buildingId)
    }
}

data class SavedBuildingItem(
    val onClickItem: (buildingId: Long) -> Unit,
    val savedBuildings: FavoriteBuilding
) {

    fun onItemClick() {
        onClickItem(savedBuildings.buildingId)
    }
}
