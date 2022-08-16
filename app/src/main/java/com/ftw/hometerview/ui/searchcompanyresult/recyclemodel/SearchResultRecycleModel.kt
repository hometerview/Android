package com.ftw.hometerview.ui.searchcompanyresult.recyclemodel

import com.ftw.domain.entity.SearchCompanyResult

class SearchResultRecycleModel(val result: SearchCompanyResult) {

    lateinit var itemClickListener: (result: SearchCompanyResult) -> Unit?

    fun onItemClick() {
        itemClickListener(result)
    }

}
