package com.ftw.hometerview.ui.writtenreview.writtenreviewlist

import com.ftw.domain.entity.WrittenReview
import com.ftw.domain.usecase.writtenreview.GetWrittenReviewsUseCase
import com.ftw.hometerview.BR
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.RecyclerItem
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class WrittenReviewListViewModel(
    dispatcher: Dispatcher,
    private val getWrittenReviewsUseCase: GetWrittenReviewsUseCase
) {

    sealed class State {
        object Loading : State()
        class OnClickReview(val buildingId: Long) : State()
    }

    private val _state: MutableStateFlow<State> = MutableStateFlow(
        State.Loading
    )
    val state: StateFlow<State> = _state.asStateFlow()

    val writtenRevieewItems: StateFlow<List<RecyclerItem>> =
        flow {
            emit(
                getWrittenReviewsUseCase()
                    .map { writtenReview ->
                        RecyclerItem(
                            data = WrittenReviewItem(
                                onClickItem = { buildingId ->
                                    _state.value = State.OnClickReview(buildingId)
                                },
                                writtenReview = writtenReview
                            ),
                            layoutId = R.layout.list_item_written_review,
                            variableId = BR.item
                        )
                    }
            )
        }.stateIn(CoroutineScope(dispatcher.ui()), SharingStarted.Eagerly, emptyList())

}

data class WrittenReviewItem(
    val onClickItem: (buildingId: Long) -> Unit,
    val writtenReview: WrittenReview
) {

    fun onItemClick() {
        onClickItem(writtenReview.buildingId)
    }
}