package com.ftw.data.remote.api

import com.ftw.data.remote.response.SearchUserResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginAPI {
    @GET("/search/users")
    suspend fun searchUser(
        @Query("q") keyword: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): SearchUserResponseData
}
