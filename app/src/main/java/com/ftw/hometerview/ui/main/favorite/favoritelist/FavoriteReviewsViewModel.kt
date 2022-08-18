package com.ftw.hometerview.ui.main.favorite.favoritelist

import com.ftw.domain.entity.FavoriteReview
import com.ftw.domain.usecase.favorite.GetFavoriteReviewsUseCase
import com.ftw.hometerview.BR
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.RecyclerItem
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class FavoriteReviewsViewModel(
    dispatcher: Dispatcher,
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

    val favoriteReviewsItems: StateFlow<List<RecyclerItem>> =
        flow {
            emit(
                getFavoriteReviewsUseCase()
                    .map { reviews ->
                        RecyclerItem(
                            data = FavoriteReviewItem(
                                onClickItem = { buildingId ->
                                    _state.value = State.OnClickReview(buildingId)
                                },
                                favoriteReviews = reviews
                            ),
                            layoutId = R.layout.list_item_favorite_review,
                            variableId = BR.item
                        )
                    }
            )
        }
            .stateIn(CoroutineScope(dispatcher.ui()), SharingStarted.Eagerly, emptyList())

}

data class FavoriteReviewItem(
    val onClickItem: (buildingId: Long) -> Unit,
    val favoriteReviews: FavoriteReview
) {

    fun onItemClick() {
        onClickItem(favoriteReviews.buildingId)
    }
}
