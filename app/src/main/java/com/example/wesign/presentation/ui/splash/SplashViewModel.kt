package com.example.wesign.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wesign.utils.DataPreferences
import com.example.wesign.utils.IS_FIRST_APP
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val dataPreferences: DataPreferences) :
    ViewModel() {
    private var _isFirstApp = MutableStateFlow(true)
    val isFirstApp = _isFirstApp.asStateFlow()

    private var _token = MutableStateFlow("")
    val token = _token.asStateFlow()

    init {
        getIsFirstApp()
        getToken()
    }

    private fun getToken() {
        viewModelScope.launch {
            val token = dataPreferences.getToken()
            if (token != null) {
                _token.value = token
            }
        }
    }

    private fun getIsFirstApp() {
        viewModelScope.launch {
            val selectedLanguage = dataPreferences.getDecryptedString(IS_FIRST_APP, Boolean::class.java)
            selectedLanguage.collect {
                if (it != null) {
                    _isFirstApp.value = it
                } else {
                    _isFirstApp.value = true
                }
            }
        }
    }
}