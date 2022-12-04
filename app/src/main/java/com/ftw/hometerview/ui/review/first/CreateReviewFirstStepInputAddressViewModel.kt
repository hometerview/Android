package com.ftw.hometerview.ui.review.first

import com.ftw.domain.usecase.search.GetSearchedBuildingAddressesUseCase
import com.ftw.hometerview.BR
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.RecyclerItem
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transformLatest

class CreateReviewFirstStepInputAddressViewModel(
    dispatcher: Dispatcher,
    private val getSearchedBuildingAddressesUseCase: GetSearchedBuildingAddressesUseCase
) {

    sealed class Event {
        object Nothing : Event()
        data class Error(val throwable: Throwable) : Event()
        data class OnClickAddress(val buildingId: String) : Event()
    }

    private val _event: MutableStateFlow<Event> = MutableStateFlow(Event.Nothing)
    val event: StateFlow<Event> = _event.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    val address: MutableStateFlow<String> = MutableStateFlow("")

    val addressItems: StateFlow<List<RecyclerItem>> =
        address
            .debounce(500)
            .transformLatest { address ->
                flow {
                    if (address.isNotBlank()) emit(getSearchedBuildingAddressesUseCase(address))
                    else emit(emptyList())
                }
                    .onStart { _isLoading.value = true }
                    .onCompletion { _isLoading.value = false }
                    .catch { _event.value = Event.Error(it) }
                    .collect { addresses ->
                        if (addresses.isNotEmpty()) {
                            emit(
                                addresses.map { searchedAddress ->
                                    RecyclerItem(
                                        data = CreateReviewAddressItem(
                                            buildingId = searchedAddress.id,
                                            onClick = { _event.value = Event.OnClickAddress(it) }
                                        ),
                                        layoutId = R.layout.list_item_create_review_search_address,
                                        variableId = BR.item
                                    )
                                }
                            )
                        } else {
                            emit(emptyList<RecyclerItem>())
                        }
                    }
            }.stateIn(CoroutineScope(dispatcher.ui()), SharingStarted.Eagerly, emptyList())
}

data class CreateReviewAddressItem(
    val buildingId: String,
    val onClick: (String) -> Unit
) {
    fun onClick() {
        this.onClick(buildingId)
    }
}
