package com.fangga.suitmediamobiledeveloperintern2023.di

import com.fangga.suitmediamobiledeveloperintern2023.data.paging.UserPagingSource
import com.fangga.suitmediamobiledeveloperintern2023.data.source.remote.api.ApiConfig

object Injection {
    fun providePagingSource(): UserPagingSource {
        val apiService = ApiConfig.getApiService()
        return UserPagingSource(apiService)
    }
}