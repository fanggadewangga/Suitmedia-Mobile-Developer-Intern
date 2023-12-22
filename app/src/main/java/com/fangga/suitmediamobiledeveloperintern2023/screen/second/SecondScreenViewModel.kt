package com.fangga.suitmediamobiledeveloperintern2023.screen.second

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SecondScreenViewModel : ViewModel() {
    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    fun setName(username: String?) {
        if (username != null)
            _username.value = username
    }
}