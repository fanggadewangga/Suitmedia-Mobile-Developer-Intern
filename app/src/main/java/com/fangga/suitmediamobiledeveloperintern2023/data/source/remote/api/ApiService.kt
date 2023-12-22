package com.fangga.suitmediamobiledeveloperintern2023.data.source.remote.api

import com.fangga.suitmediamobiledeveloperintern2023.data.source.remote.response.BaseResponse
import com.fangga.suitmediamobiledeveloperintern2023.data.source.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun fetchUsers(
        @Query("page") page: Int,
    ): BaseResponse<List<UserResponse>>
}