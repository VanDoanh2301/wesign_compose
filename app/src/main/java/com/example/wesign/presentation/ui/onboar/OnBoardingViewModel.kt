package com.example.wesign.presentation.ui.onboar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wesign.utils.DataPreferences
import com.example.wesign.utils.IS_FIRST_APP
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val dataPreferences: DataPreferences
): ViewModel() {
    fun onEvent(event: OnBoardingScreenEvent) {
        when (event) {
            is OnBoardingScreenEvent.setFirstApp -> {
                setFirstApp(event.isFirstApp)
            }
        }
    }
    private fun setFirstApp(firstApp: Boolean) {
        viewModelScope.launch {
            dataPreferences.saveEncryptedString(IS_FIRST_APP, firstApp)
        }
    }
}