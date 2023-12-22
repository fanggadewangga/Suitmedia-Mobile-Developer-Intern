package com.fangga.suitmediamobiledeveloperintern2023.screen.first

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

class FirstScreenViewModel : ViewModel() {
    private val _isPalindrome = MutableStateFlow(false)
    val isPalindrome = _isPalindrome.asStateFlow()

    fun isPalindrome(inputText: String) {
        val cleanedInput = inputText.replace("\\s".toRegex(), "")
        val reversedInputText = cleanedInput.reversed()
        if (inputText != "")
            _isPalindrome.value = cleanedInput.equals(other = reversedInputText, ignoreCase = true)
        else
            _isPalindrome.value = false
    }
}