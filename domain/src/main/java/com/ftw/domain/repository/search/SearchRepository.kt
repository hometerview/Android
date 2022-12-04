package com.ftw.domain.repository.search

import com.ftw.domain.entity.Address

interface SearchRepository {
    suspend fun buildings(keyword: String): List<Address>
}
