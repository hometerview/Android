package com.ftw.data.datasource.search

import com.ftw.domain.entity.Address

interface SearchDataSource {
    suspend fun buildings(keyword: String): List<Address>
}
