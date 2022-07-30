package com.ftw.hometerview.ui.onboardingresult.recyclemodel

import com.ftw.domain.entitiy.SearchResult

class SearchResultRecycleModel(val result: SearchResult) {

    lateinit var itemClickHandler: (result: SearchResult) -> Unit

    fun onItemClick() {
        itemClickHandler(result)
    }

}
