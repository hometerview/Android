package com.ftw.hometerview.ui.myreviews

import com.ftw.domain.entity.WrittenReview
import com.ftw.domain.usecase.myreviews.GetMyReviewsUseCase
import com.ftw.hometerview.BR
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.RecyclerItem
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class MyReviewsViewModel(
    dispatcher: Dispatcher,
    private val getMyReviewsUseCase: GetMyReviewsUseCase
) {

    sealed class State {
        object Loading : State()
        class OnClickReview(val buildingId: Long) : State()
    }

    sealed class Event {
        object None : Event()
        object onClickWriteReview : Event()
    }

    private val _event: MutableStateFlow<Event> = MutableStateFlow(Event.None)
    val event: StateFlow<Event> = _event.asStateFlow()

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    val myReviewsItems: StateFlow<List<RecyclerItem>> =
        flow {
            emit(
                getMyReviewsUseCase()
                    .map { writtenReview ->
                        RecyclerItem(
                            data = MyReviewItem(
                                onClickItem = { buildingId ->
                                    _state.value = State.OnClickReview(buildingId)
                                },
                                myReview = writtenReview
                            ),
                            layoutId = R.layout.list_item_my_review,
                            variableId = BR.item
                        )
                    }
            )
        }.stateIn(CoroutineScope(dispatcher.ui()), SharingStarted.Eagerly, emptyList())

    fun onClickWriteReview() {
        _event.value = Event.onClickWriteReview
        _event.value = Event.None
    }
}

data class MyReviewItem(
    val onClickItem: (buildingId: Long) -> Unit,
    val myReview: WrittenReview
) {

    fun onItemClick() {
        onClickItem(myReview.buildingId)
    }
}
