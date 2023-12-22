package com.fangga.suitmediamobiledeveloperintern2023.screen.first

import androidx.lifecycle.ViewModel

class FirstScreenViewModel : ViewModel() {
    fun isPalindrome(inputText: String): Boolean {
        val cleanedInput = inputText.replace("\\s".toRegex(), "")
        val reversedInputText = cleanedInput.reversed()
        return if (inputText != "")
            cleanedInput.equals(other = reversedInputText, ignoreCase = true)
        else
            false
    }
}