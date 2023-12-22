package com.fangga.suitmediamobiledeveloperintern2023.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fangga.suitmediamobiledeveloperintern2023.data.paging.UserPagingSource
import com.fangga.suitmediamobiledeveloperintern2023.di.Injection
import com.fangga.suitmediamobiledeveloperintern2023.screen.first.FirstScreenViewModel
import com.fangga.suitmediamobiledeveloperintern2023.screen.second.SecondScreenViewModel
import com.fangga.suitmediamobiledeveloperintern2023.screen.third.ThirdScreenViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val pagingSource: UserPagingSource) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstScreenViewModel::class.java)) {
            return FirstScreenViewModel() as T
        }
        if (modelClass.isAssignableFrom(SecondScreenViewModel::class.java)) {
            return SecondScreenViewModel() as T
        }
        if (modelClass.isAssignableFrom(ThirdScreenViewModel::class.java)) {
            return ThirdScreenViewModel(pagingSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.providePagingSource())
            }.also { instance = it }
        }
    }
}