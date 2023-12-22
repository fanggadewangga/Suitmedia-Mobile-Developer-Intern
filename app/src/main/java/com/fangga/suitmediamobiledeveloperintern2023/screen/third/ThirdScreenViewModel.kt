package com.fangga.suitmediamobiledeveloperintern2023.screen.third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.fangga.suitmediamobiledeveloperintern2023.data.paging.UserPagingSource
import com.fangga.suitmediamobiledeveloperintern2023.model.User
import com.fangga.suitmediamobiledeveloperintern2023.util.RemoteState
import com.fangga.suitmediamobiledeveloperintern2023.util.toUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class ThirdScreenViewModel(private val userPagingSource: UserPagingSource) : ViewModel() {
    private val _remoteState = MutableStateFlow(RemoteState.LOADING)
    val remoteState = _remoteState.asStateFlow()

    private val _listUserState = MutableStateFlow<PagingData<User>?>(null)
    val listUserState = _listUserState.asStateFlow()

    suspend fun fetchUsers() {
        _remoteState.value = RemoteState.LOADING
        Pager(
            config = PagingConfig(pageSize = 6, maxSize = 18),
            pagingSourceFactory = { userPagingSource },
        ).flow
            .map { it.map { response -> response.toUser() } }
            .cachedIn(viewModelScope)
            .catch {
                _remoteState.value = RemoteState.ERROR
            }
            .collect {
                _listUserState.value = it
                _remoteState.value = RemoteState.SUCCESS
            }
    }
}