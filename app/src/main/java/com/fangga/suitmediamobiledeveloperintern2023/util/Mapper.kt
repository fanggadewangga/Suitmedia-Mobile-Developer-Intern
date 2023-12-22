package com.fangga.suitmediamobiledeveloperintern2023.util

import com.fangga.suitmediamobiledeveloperintern2023.data.source.remote.response.UserResponse
import com.fangga.suitmediamobiledeveloperintern2023.model.User

fun UserResponse.toUser() = User(
    id, email, firstName, lastName, avatar
)