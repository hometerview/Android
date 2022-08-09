package com.ftw.hometerview.ui.searchcompanyresult

import android.util.Log
import com.ftw.hometerview.BR
import androidx.lifecycle.ViewModel
import com.ftw.domain.entity.DEMO_COMPANY_RESULTS
import com.ftw.domain.entity.SearchCompanyResult
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.RecyclerItem
import com.ftw.hometerview.dispatcher.Dispatcher
import com.ftw.hometerview.ui.login.TAG
import com.ftw.hometerview.ui.searchcompanyresult.recyclemodel.SearchResultRecycleModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchCompanyResultViewModel(
    private val dispatcher: Dispatcher
) : ViewModel() {

//    private val _recyclerItems = MutableLiveData<List<RecyclerItem>>()
//    val recyclerItems: LiveData<List<RecyclerItem>> = _recyclerItems
    private val _recyclerItems: MutableStateFlow<List<RecyclerItem>> = MutableStateFlow(listOf())
    val recyclerItems: StateFlow<List<RecyclerItem>> get() = _recyclerItems.asStateFlow()
    private val _clickState: MutableStateFlow<State> = MutableStateFlow(State.Nothing)
    val clickState: StateFlow<State> get() = _clickState.asStateFlow()

    sealed class State {
        object OnClickEmpty : State()
        object Failure : State()
        object Loading : State()
        object Nothing : State()
    }

    init {
        _recyclerItems.value = loadDemoDatas()
            .map { createSearchResultRecycleModel(it) }
            .map { it.toRecyclerItem() }

    }

    private fun createSearchResultRecycleModel(searchResult: SearchCompanyResult): SearchResultRecycleModel {
        return SearchResultRecycleModel(searchResult).apply {
            itemClickListener = { searchResult -> showClickMessage(searchResult) }
        }
    }

    private fun showClickMessage(searchResult: SearchCompanyResult) {
        //통신으로 받아온 정보를 State와 조합해서 사용하면 될 듯 함

    }

    fun noResultRecycleModel() {
        Log.d(TAG, "카카오계정공123 ${_clickState.value}")
        CoroutineScope(dispatcher.ui()).launch {
            _clickState.value = State.Loading
            // api 호출??
            _clickState.value = State.OnClickEmpty

        }

    }

    fun noResultClicked() {
        CoroutineScope(dispatcher.ui()).launch {
            _clickState.value = State.Nothing

        }
    }

    private fun loadDemoDatas(): List<SearchCompanyResult> = DEMO_COMPANY_RESULTS

}

private fun SearchResultRecycleModel.toRecyclerItem() = RecyclerItem(
    data = this,
    layoutId = R.layout.list_item_company_search_result,
    variableId = BR.searchResult
)
