package com.example.wesign.presentation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.wesign.utils.SharedPreferencesUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ThemeViewModel : ViewModel() {
    private val  _isDarkThemeEnabled = MutableStateFlow(false)
        val isDarkThemeEnabled = _isDarkThemeEnabled

    fun setTheme(isDarkTheme: Boolean) {
        _isDarkThemeEnabled.value = isDarkTheme
        SharedPreferencesUtils.setBoolean("THEME_DARK", isDarkTheme)
    }
}