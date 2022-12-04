package com.ftw.data.repository.search

import com.ftw.data.datasource.search.SearchDataSource
import com.ftw.domain.entity.Address
import com.ftw.domain.repository.search.SearchRepository

class SearchRepositoryImpl(private val dataSource: SearchDataSource) : SearchRepository {
    override suspend fun buildings(keyword: String): List<Address> {
        return dataSource.buildings(keyword)
    }
}
