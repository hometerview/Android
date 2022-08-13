package com.ftw.hometerview.ui.searchcompanyresult.recyclemodel

import com.ftw.domain.entitiy.SearchResult

class SearchResultRecycleModel(val result: SearchResult) {

    lateinit var itemClickListener: (result: SearchResult) -> Unit?

    fun onItemClick() {
        itemClickListener(result)
    }

}