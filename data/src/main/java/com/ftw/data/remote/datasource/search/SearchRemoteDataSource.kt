package com.ftw.data.remote.datasource.search

import com.ftw.data.datasource.search.SearchDataSource
import com.ftw.data.remote.api.SearchAPI
import com.ftw.data.remote.exception.ResponseException
import com.ftw.data.remote.response.address.toEntity
import com.ftw.domain.entity.Address

class SearchRemoteDataSource(private val api: SearchAPI) : SearchDataSource {
    override suspend fun buildings(keyword: String): List<Address> {
        return try {
            val response = api.buildings(keyword)
            if (response.isSuccessful) {
                response.body()?.data?.map { it.toEntity() } ?: emptyList()
            } else {
                throw ResponseException("Network Exception")
            }
        } catch (e: Exception) {
            throw e
        }
    }
}
