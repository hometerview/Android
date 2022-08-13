package com.ftw.hometerview.ui.review.first

import com.ftw.domain.usecase.address.GetAddressUseCase
import com.ftw.hometerview.BR
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.RecyclerItem
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transformLatest

class CreateReviewFirstStepViewModel(
    dispatcher: Dispatcher,
    private val getAddressUseCase: GetAddressUseCase
) {

    val address: MutableStateFlow<String> = MutableStateFlow("")

    val addressItems: StateFlow<List<RecyclerItem>> =
        address
            .debounce(500)
            .transformLatest { address ->
                flow<Result<List<String>>> {
                    if (address.isNotBlank()) emit(getAddressUseCase(address))
                    else emit(Result.success(emptyList()))
                }.collect { result ->
                    if (result.isSuccess && result.getOrNull() != null) {
                        emit(
                            result.getOrDefault(emptyList())
                                .map { searchedAddress ->
                                    RecyclerItem(
                                        data = searchedAddress,
                                        layoutId = R.layout.list_item_create_review_search_address,
                                        variableId = BR.address
                                    )
                                }
                        )
                    } else {
                        emit(emptyList<RecyclerItem>())
                    }
                }
            }.stateIn(CoroutineScope(dispatcher.ui()), SharingStarted.Eagerly, emptyList())

}
