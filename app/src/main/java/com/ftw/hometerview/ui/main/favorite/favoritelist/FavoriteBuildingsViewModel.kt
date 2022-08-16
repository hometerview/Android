package com.ftw.hometerview.ui.main.favorite.favoritelist

import com.ftw.domain.entity.FavoriteBuilding
import com.ftw.domain.usecase.favorite.GetFavoriteBuildingsUseCase
import com.ftw.hometerview.BR
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.RecyclerItem
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class FavoriteBuildingsViewModel(
    dispatcher: Dispatcher,
    private val getFavoriteBuildingsUseCase: GetFavoriteBuildingsUseCase
) {

    sealed class State {
        object Loading : State()
        class OnClickReview(val buildingId: Long) : State()
    }

    private val _state: MutableStateFlow<State> = MutableStateFlow(
        State.Loading
    )
    val state: StateFlow<State> = _state.asStateFlow()

    private val _favoriteBuildings: MutableStateFlow<List<FavoriteBuilding>> =
        MutableStateFlow(emptyList())
    private val favoriteBuildings: StateFlow<List<FavoriteBuilding>> = _favoriteBuildings.asStateFlow()

    val favoriteBuildingsItems: StateFlow<List<RecyclerItem>> =
        favoriteBuildings.transformLatest {
            flow {
                emit(
                    getFavoriteBuildingsUseCase()
                        .map { building ->
                            RecyclerItem(
                                data = FavoriteBuildingItem(
                                    onClickItem = { buildingId ->
                                        _state.value = State.OnClickReview(buildingId)
                                    },
                                    favoriteBuildings = building
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

data class FavoriteBuildingItem(
    val onClickItem: (buildingId: Long) -> Unit,
    val favoriteBuildings: FavoriteBuilding
) {

    fun onItemClick() {
        onClickItem(favoriteBuildings.buildingId)
    }
}
