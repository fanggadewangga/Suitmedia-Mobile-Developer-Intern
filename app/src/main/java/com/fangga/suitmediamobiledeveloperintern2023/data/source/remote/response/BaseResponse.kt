package com.fangga.suitmediamobiledeveloperintern2023.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("page")
    val page: Int,

    @SerializedName("per_page")
    val perPage: Int,

    @SerializedName("total")
    val total: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("data")
    val data: T,
)
