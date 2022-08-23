package com.ftw.hometerview.ui.writtenreview

import com.ftw.domain.entity.WrittenReview
import com.ftw.domain.usecase.writtenreview.GetWrittenReviewsUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class WrittenReviewViewModel(
    dispatcher: Dispatcher,
    private val getWrittenReviewsUseCase: GetWrittenReviewsUseCase
) {

    val writtenRevieewItems: StateFlow<List<WrittenReview>> =
        flow {
            emit(
                getWrittenReviewsUseCase()
            )
        }.stateIn(CoroutineScope(dispatcher.ui()), SharingStarted.Eagerly, emptyList())
}
