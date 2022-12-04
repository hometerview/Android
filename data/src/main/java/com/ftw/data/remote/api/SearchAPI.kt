package com.ftw.data.remote.api

import com.ftw.data.remote.response.RemoteResponse
import com.ftw.data.remote.response.address.AddressResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {
    @GET("/api/v1/place")
    suspend fun buildings(
        @Query("keyword") keyword: String
    ): Response<RemoteResponse<List<AddressResponse>>>
}
