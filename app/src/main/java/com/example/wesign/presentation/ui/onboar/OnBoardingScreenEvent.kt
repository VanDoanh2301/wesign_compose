package com.example.wesign.presentation.ui.onboar

sealed class OnBoardingScreenEvent {
    data class setFirstApp(val isFirstApp: Boolean) : OnBoardingScreenEvent()
}